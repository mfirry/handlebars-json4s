package com.github.mfirry
package handlebars.json4s

import org.specs2.mutable._

import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.JsonDSL._

import com.github.jknack.handlebars.{ Handlebars, Context }

class JValueResolverTest extends Specification {

   val handlebars = new Handlebars()

  "Parsing json" should {
    "produce the expected output" in {
      val json = ("string" -> "abc") ~
        ("int" -> 678) ~ ("long" -> 6789L) ~
        ("float" -> 7.13f) ~
        ("double" -> 3.14d) ~ ("bool" -> true)

        val context = Context.newBuilder(json).resolver(JValueResolver.INSTANCE).build

        val template = handlebars.compileInline("{{string}} {{int}} {{long}} {{float}} {{double}} {{bool}}")

        template.apply(context) must be_==("abc 678 6789 7.130000114440918 3.14 true")
      }
    "null must be resolved to UNRESOLVED" in {
       JValueResolver.INSTANCE.resolve(null, "nothing") must be_==(com.github.jknack.handlebars.ValueResolver.UNRESOLVED)
      }      
    }
  }
