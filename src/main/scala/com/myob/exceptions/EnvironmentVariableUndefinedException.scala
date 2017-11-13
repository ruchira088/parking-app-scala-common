package com.myob.exceptions

case class EnvironmentVariableUndefinedException(envName: String) extends Exception