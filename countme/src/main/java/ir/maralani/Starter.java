package ir.maralani;

import io.vertx.core.Vertx;

import java.util.ArrayList;
import java.util.List;

public class Starter {
    public static List<String> list = new ArrayList<>();
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new ServerOne());
        vertx.deployVerticle(new ServerTwo());
        vertx.deployVerticle(new ServerThree());
        vertx.deployVerticle(new ServerFour());
    }

}
