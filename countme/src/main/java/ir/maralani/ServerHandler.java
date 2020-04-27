package ir.maralani;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.apache.commons.lang3.StringUtils;

public class ServerHandler extends SimpleChannelInboundHandler<Object> {
    private int count = 0;

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) {
        FullHttpRequest request = (FullHttpRequest) msg;
        if (request.uri().equals("/") && request.method().equals(HttpMethod.POST)) {
            String stringBody = request.content().toString(CharsetUtil.UTF_8);
            count += Integer.parseInt(StringUtils.trim(stringBody));
            writeResponse(ctx, request, Unpooled.EMPTY_BUFFER, "text/html", String.valueOf(String.valueOf(count).length()));
            return;
        }

        if (request.uri().equals("/count") && request.method().equals(HttpMethod.GET)) {
            ByteBuf responseContent = Unpooled.copiedBuffer(String.valueOf(count), CharsetUtil.UTF_8);
            writeResponse(ctx, request, responseContent, "text/html", String.valueOf(String.valueOf(count).length()));
            return;
        }

        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NOT_FOUND, Unpooled.EMPTY_BUFFER, false);
        ctx.write(response).addListener(ChannelFutureListener.CLOSE);

    }

    private void writeResponse(ChannelHandlerContext ctx, HttpRequest request, ByteBuf buf, CharSequence contentType, CharSequence contentLength) {
        // Decide whether to close the connection or not.
        boolean keepAlive = HttpUtil.isKeepAlive(request);
        // Build the response object.
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buf, false);
        HttpHeaders headers = response.headers();
        headers.set(HttpHeaderNames.CONTENT_TYPE, contentType);

        // Close the non-keep-alive connection after the write operation is
        // done.
            ctx.write(response).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
