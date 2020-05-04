package ir.maralani;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import org.apache.commons.lang3.StringUtils;

public class ServerTwo extends AbstractVerticle {
    @Override
    public void start() {
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());

        router.route().path("/").method(HttpMethod.POST).handler(routingContext -> {
            String body = StringUtils.trim(routingContext.getBodyAsString());
            Starter.list.add(body);
            routingContext.response().setStatusCode(200).end();
        });
        router.route().path("/count").method(HttpMethod.GET).handler(routingContext -> {
            routingContext.response().setStatusCode(200).end(String.valueOf(Starter.list.stream().mapToInt(Integer::valueOf).sum()));
        });
        server.requestHandler(router).listen(80);
    }
}
