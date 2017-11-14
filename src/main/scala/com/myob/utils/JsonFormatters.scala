package com.myob.utils

import org.joda.time.DateTime
import play.api.libs.json._

object JsonFormatters
{
  implicit def dateTimeObjectFormat: Format[DateTime] = new Format[DateTime]
  {
    override def reads(jsValue: JsValue): JsResult[DateTime] = jsValue match
    {
      case JsString(string) =>
        ParseUtils.dateTime(string)
          .fold(
            throwable => JsError(throwable.getMessage),
            JsSuccess(_)
          )
      case _ => JsError()
    }

    override def writes(dateTime: DateTime): JsValue =
      JsString(dateTime.toString)
  }
}
