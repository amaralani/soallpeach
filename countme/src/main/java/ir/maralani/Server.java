package ir.maralani;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

public class Server {
    private static List<String> list = new ArrayList<>();

    public static void main(String[] args) {
        port(80);
        post("/", (request, response) -> {
            list.add(request.body());
            return "";
        });
        get("/count", (request, response) ->
                list.stream().mapToInt(stringItem -> Integer.valueOf(StringUtils.trim(stringItem))).sum());
    }
}
