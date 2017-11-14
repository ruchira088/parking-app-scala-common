package com.myob.utils

import com.myob.exceptions.JsonDeserializationException
import play.api.libs.json._
import play.api.mvc.Request

import scala.util.control.NonFatal
import scala.util.{Failure, Success, Try}

object JsonUtils
{
  def deserialize[A](jsValue: JsValue)(implicit reads: Reads[A]): Try[A] =
    jsValue.validate.fold[Try[A]](
      validationErrors => Failure(JsonDeserializationException(validationErrors)),
      Success(_)
    )

  def deserialize[A](implicit request: Request[JsValue], reads: Reads[A]): Try[A] =
    deserialize[A](request.body)

  def parse(string: String): Try[JsValue] =
    try {
      Success(Json.parse(string))
    } catch {
      case NonFatal(throwable) => Failure(throwable)
    }
}
