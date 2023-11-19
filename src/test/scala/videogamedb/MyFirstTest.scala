package videogamedb

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class MyFirstTest extends Simulation
{
  //http config
  val httpProtocal = http.baseUrl(url="https://videogamedb.uk/api")
    .acceptHeader(value="application/json")

  //  scenario def
  val scn = scenario(name="My First Test")
    .exec(
      http("Get all games")
        .get("/videogame")
    )

  //  load scenario
  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocal)
}
