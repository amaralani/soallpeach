package ir.maralani;

import io.vertx.core.Vertx;

import java.util.concurrent.atomic.AtomicInteger;

public class Starter {
    public static AtomicInteger count = new AtomicInteger();

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new ServerOne());
        vertx.deployVerticle(new ServerTwo());
        vertx.deployVerticle(new ServerThree());
        vertx.deployVerticle(new ServerFour());
    }

}
