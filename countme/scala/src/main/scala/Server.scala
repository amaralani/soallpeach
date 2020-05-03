import java.util.{ArrayList, List}

import org.apache.commons.lang3.StringUtils
import spark.Spark
import spark.Spark._


object Server extends Spark with App {

  private var list: List[String] = new ArrayList()

  port(80)

  post("/", (request, response) => {
    list.add(request.body())
    ""
  })
  get("/count",
    (request, response) =>
      list
        .stream()
        .mapToInt((stringItem) =>
          java.lang.Integer.valueOf(StringUtils.trim(stringItem)))
        .sum())


}
