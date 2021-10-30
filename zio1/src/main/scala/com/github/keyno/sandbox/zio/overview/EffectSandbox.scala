package com.github.keyno.sandbox.zio.overview

import zio.console.{getStrLn, putStrLn}
import zio.{Task, UIO, ZIO}

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

}