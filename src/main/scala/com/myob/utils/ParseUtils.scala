package com.myob.utils

import org.joda.time.DateTime

import scala.util.{Failure, Success, Try}
import scala.util.control.NonFatal

object ParseUtils
{
  def dateTime(string: String): Try[DateTime] = try
  {
    Success(DateTime.parse(string))
  } catch {
    case NonFatal(throwable) => Failure(throwable)
  }
}