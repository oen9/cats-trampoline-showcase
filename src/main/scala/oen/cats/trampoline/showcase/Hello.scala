package oen.cats.trampoline.showcase
import scala.io.StdIn
import cats.effect.IO
import cats.effect.IOApp
import cats.effect.ExitCode

object Main extends IOApp {

  def putStrLn(s: String) = IO.delay(println(s))
  def readLn = IO.delay(StdIn.readLine)

  def run(args: List[String]): IO[ExitCode] = for {
    _ <- putStrLn("type \"y\" for flatMap outside for\nanything else for flatMap inside for (terminate to stop)")
    line <- readLn
    _ <- if (line == "y") outsideFor() else insideFor()
  } yield ExitCode.Success

  def printAndIncrease(number: Long): IO[Long] = for {
    _ <- IO.unit
    _ <- (if (number % 1000000 == 0) putStrLn(s"number: $number") else IO.unit)
  } yield number + 1

  def insideFor(number: Long = 0): IO[Unit] = for {
    newNumber <- printAndIncrease(number)
    _ <- insideFor(newNumber)
  } yield ()

  // def outsideFor(number: Long = 0): IO[Unit] = printAndIncrease(number).flatMap(outsideFor)
  def outsideFor(number: Long = 0): IO[Unit] = (for {
    newNumber <- printAndIncrease(number)
  } yield newNumber).flatMap(outsideFor)
}
