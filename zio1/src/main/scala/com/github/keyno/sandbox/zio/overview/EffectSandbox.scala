package com.github.keyno.sandbox.zio.overview

import zio.console.{getStrLn, putStrLn}
import zio.{IO, Task, UIO, ZIO}

object EffectSandbox extends zio.App {
  final def run(args: List[String]) =
    myAppLogic.exitCode

  def myAppLogic =
    for {
      _ <- putStrLn("Hello! What is your name?")
      n <- getStrLn
      z <- new FromSuccessValues().createZioSucceed(n)
      _ <- putStrLn("Hello, " + z + ", good to meet you!")
    } yield ()
}

class FromSuccessValues {
  def createZioSucceed(intStr: String): UIO[Int] = {
    ZIO.succeed(intStr.toInt)
  }

  // same as createZioSucceed
  def createTaskSucceed(intStr: String): UIO[Int] = {
    Task.succeed(intStr.toInt)
  }

  // totally. if not exist side-effect
  def createZioEffectTotal(intVal: Int): UIO[Int] = {
    ZIO.effectTotal(intVal)
  }
}

class FromFailureValues {
  def createZioFailure(message: String) = {
    ZIO.fail(message)
  }

  def createTaskFailure(exception: Exception) = {
    Task.fail(exception)
  }
}

class FromScalaValues {
  // fron Option
  def createZioOption() = {
    ZIO.fromOption(Option(2))
  }

  def convertErrorToString(zioOption: IO[Option[Nothing], Int]) = {
    zioOption.mapError(_ => "It wasn't there")
  }

  case class User(teamId: String)
  case class Team()
  def sampleCode(): ZIO[Any, Throwable, Option[(User, Team)]] = {

    val maybeId: IO[Option[Nothing], String] = ZIO.fromOption(Some("abc123"))
    def getUser(userId: String): IO[Throwable, Option[User]] = ???
    def getTeam(teamId: String): IO[Throwable, Team] = ???


    // should convert to Any, Option[Throwable], (User, Team)
    (for {
      id   <- maybeId
      user <- getUser(id).some
      team <- getTeam(user.teamId).asSomeError
    } yield (user, team)).optional
    // unsome from zio 2.x
  }

  // from Either
  def createFromEither(value: String) = {
    ZIO.fromEither(Right(value))
  }

  // from Try
  def createFromTry() = {
    import scala.util.Try
    ZIO.fromTry(Try(42/0))
  }

  // from Future
  def createFromFuture(): Task[String] = {
    import scala.concurrent.Future

    lazy val future = Future.successful("Hello!")

    ZIO.fromFuture { implicit ec =>
      future.map(_ => "Goodbye!")
    }
  }

}