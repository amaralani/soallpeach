package ir.maralani;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.apache.commons.lang3.StringUtils;

public class ServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    private static int count = 0;

    @Override
    public void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) {
        if (request.uri().equals("/") && request.method().equals(HttpMethod.POST)) {
            String stringBody = request.content().toString(CharsetUtil.UTF_8);
            count += Integer.parseInt(StringUtils.trim(stringBody));
            writeResponse(ctx, Unpooled.EMPTY_BUFFER);
            return;
        }

        if (request.uri().equals("/count") && request.method().equals(HttpMethod.GET)) {
            ByteBuf responseContent = Unpooled.copiedBuffer(String.valueOf(count), CharsetUtil.UTF_8);
            writeResponse(ctx, responseContent);
            return;
        }

        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NOT_FOUND,
                Unpooled.EMPTY_BUFFER, false);
        ctx.write(response).addListener(ChannelFutureListener.CLOSE);
    }

    private void writeResponse(ChannelHandlerContext ctx, ByteBuf buf) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buf,
                false);
        HttpHeaders headers = response.headers();
        headers.set(HttpHeaderNames.CONTENT_TYPE, "text/html");

        ctx.write(response).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }
}
