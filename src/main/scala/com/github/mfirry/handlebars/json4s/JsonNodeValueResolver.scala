package com.github.mfirry
package handlebars.json4s

import java.util.Map.Entry
import java.util.{ Set => JSet }
import java.util.AbstractMap.SimpleImmutableEntry

import scala.collection.JavaConversions._

import com.github.jknack.handlebars.ValueResolver
import com.github.jknack.handlebars.ValueResolver._

import org.json4s._
import org.json4s.jackson.JsonMethods._

class JsonNodeValueResolver extends ValueResolver {

  def resolve(context: AnyRef, name: String): AnyRef = context match {
    case JArray(arr) =>
      arr.lift(name.toInt) getOrElse UNRESOLVED
    case json: JObject =>
      val resolved = json \ name
      if (resolved == JNothing) {
        UNRESOLVED
      } else {
        resolved
      }
    case _ =>
      UNRESOLVED
  }

  private def blah(jfield: (String, JValue)): Entry[String, AnyRef] = new SimpleImmutableEntry(jfield._1, resolve(jfield._2, jfield._1))

  def propertySet(context: AnyRef): JSet[Entry[String, AnyRef]] = context match {
    case JObject(obj) =>
      obj map blah toSet : Set[Entry[String, AnyRef]]
    case _ => Set.empty[Entry[String, AnyRef]]
  }
}