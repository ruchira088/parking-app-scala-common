package com.myob.exceptions

case class DbInsertionError[A](item: A) extends Exception