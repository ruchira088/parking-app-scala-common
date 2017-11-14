package com.myob.types

import com.myob.exceptions.EmptyOptionException

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try

case class FutureO[A](future: Future[Option[A]])
{
  def flatMap[B](func: A => FutureO[B])(implicit executionContext: ExecutionContext): FutureO[B] =
    FutureO {
      future.flatMap {
        case Some(value) => func(value).future
        case None => Future.successful(None)
      }
    }

  def map[B](func: A => B)(implicit executionContext: ExecutionContext): FutureO[B] =
    FutureO { future.map( _ map func) }

  def flatten(emptyOptionException: => Exception = EmptyOptionException)(implicit executionContext: ExecutionContext): Future[A] =
    future.flatMap {
      case Some(value) => Future.successful(value)
      case None => Future.failed(emptyOptionException)
    }
}

object FutureO
{
  implicit def fromFuture[A](future: Future[A])(implicit executionContext: ExecutionContext): FutureO[A] =
    FutureO {
      future.map(Some(_))
    }

  implicit def fromTry[A](tryValue: Try[A])(implicit executionContext: ExecutionContext): FutureO[A] =
    fromFuture[A](Future.fromTry(tryValue))
}