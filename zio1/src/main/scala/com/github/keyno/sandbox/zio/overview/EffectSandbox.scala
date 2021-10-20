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
      z <- new Success1().createZioSucceed(n.toInt)
      _ <- putStrLn("Hello, " + z + ", good to meet you!")
    } yield ()
}

class Success1 {
  def createZioSucceed(intVal: Int): UIO[Int] = {
    ZIO.succeed(intVal)
  }

  // same as createZioSucceed
  def createTaskSucceed(intVal: Int): UIO[Int] = {
    Task.succeed(intVal)
  }

  // totally. if not exist side-effect
  def createZioEffectTotal(intVal: Int): UIO[Int] = {
    ZIO.effectTotal(intVal)
  }
}
