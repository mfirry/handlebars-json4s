package com.github.mfirry
package handlebars.json4s

import org.specs2.mutable._

import org.json4s._
import org.json4s.native.JsonMethods._
import org.json4s.JsonDSL._

import com.github.jknack.handlebars.Handlebars

class JValueResolverTest extends Specification {

   val handlebars = new Handlebars()
   val root = Map("string" -> "abc", "int" -> 678, "long" -> 6789L, "float" -> 7.13f, "double" -> 3.14d, "bool" -> true)
   
  }
  