package com.myob.utils

import com.myob.exceptions.EmptyOptionException

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

object ScalaUtils
{
  def predicate(boolean: Boolean, exception: => Exception): Future[Unit] =
    if (boolean) Future.successful((): Unit) else Future.failed(exception)

  def fromOptionToTry[A](option: Option[A], exception: => Exception = EmptyOptionException): Try[A] =
    option.fold[Try[A]](Failure(exception))(Success(_))

  def trySequence[A](tryList: List[Try[A]]): Try[List[A]] =
    if (tryList.isEmpty)
      Success(List.empty[A])
    else for {
      value <- tryList.head
      rest <- trySequence(tryList.tail)
    } yield value :: rest
}
