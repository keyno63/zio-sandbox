package com.github.keyno.sandbox.zio.gettingstart

import zio.{ Console, Has, ZIO, ZIOAppDefault }
import zio.Console.{ printLine, readLine }

import java.io.IOException

object Main extends ZIOAppDefault {
  def run: ZIO[Has[Console], IOException, Unit] = myAppLogic

  val myAppLogic: ZIO[Has[Console], IOException, Unit] =
    for {
      _    <- printLine("Hello! What is your name?")
      name <- readLine
      _    <- printLine(s"Hello, $name, welcome to ZIO!")
    } yield ()
}
