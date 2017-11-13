package com.myob.utils

import com.myob.exceptions.EnvironmentVariableUndefinedException

import scala.util.Try

object ConfigUtils
{
  def getEnvValue(envName: String): Option[String] = Option(System.getenv(envName))

  def getEnvValueAsTry(envName: String): Try[String] =
    ScalaUtils.fromOptionToTry(getEnvValue(envName), EnvironmentVariableUndefinedException(envName))
}