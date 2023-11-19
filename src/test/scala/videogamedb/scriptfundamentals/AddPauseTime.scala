package videogamedb.scriptfundamentals
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration.DurationInt

class AddPauseTime extends Simulation{
  val httpProtocol = http.baseUrl("https://videogamedb.uk/api")
    .acceptHeader("application/json")

  val scn = scenario("Video Game DB - 3 calls")
    .exec(http("Get all video games").get("/videogame"))
    .pause(5)

    .exec(http("Get specific game").get("/videogame/1"))
    .pause(1, 10)  // random pause time between 1 and 10

    .exec(http("Get all video games - 2nd call").get("/videogame"))
    .pause(3000.milliseconds)

  setUp(scn.inject(atOnceUsers(users=1)).protocols(httpProtocol))
}
