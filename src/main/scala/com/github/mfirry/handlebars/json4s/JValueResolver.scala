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

class JValueResolver extends ValueResolver {

  def resolve(context: AnyRef, name: String): AnyRef = context match {
    case JArray(arr) =>
      arr.lift(name.toInt) getOrElse UNRESOLVED
    case json: JObject =>
      val resolved = json \ name
      resolved match {
        case JNothing => UNRESOLVED
        case _ => resolved
      }
    case _ =>
      UNRESOLVED
  }

  private def unpack(jfield: (String, JValue)): Entry[String, AnyRef] =
    new SimpleImmutableEntry(jfield._1, resolve(jfield._2, jfield._1))

  def propertySet(context: AnyRef): JSet[Entry[String, AnyRef]] = context match {
    case JObject(obj) =>
      obj map unpack toSet : Set[Entry[String, AnyRef]]
    case _ => Set.empty[Entry[String, AnyRef]]
  }
}

object JValueResolver {
  val INSTANCE = new JValueResolver()
}