package videogamedb.scriptfundamentals
import io.gatling.core.Predef._
import io.gatling.http.Predef._


class CodeReuse extends Simulation {
  val httpProtocol = http.baseUrl("https://videogamedb.uk/api")
    .acceptHeader("application/json")

  def getAllVideoGames() = {
    repeat(3){
      exec(
        http("Get all video games")
          .get("/videogame")
          .check(status.is(200))
      )
    }
  }

  def getSpecificGame() = {
    repeat(5, "counter"){
      exec(
        http(s"Get specific game with id: #{counter}")
          .get("/videogame/#{counter}")
          .check(status.in(200 to 210))
      )
    }
  }

  val scn = scenario("Code reuse")
    .exec(getAllVideoGames())
    .pause(5)
    .exec(getSpecificGame())
    .pause(5)
    .repeat(2){
      getAllVideoGames()
    }

  setUp(scn.inject(atOnceUsers(users=1)).protocols(httpProtocol))
}
