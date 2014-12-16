package com.github.mfirry
package handlebars.json4s

import java.util.Map.Entry
import java.util.{ Set => JSet }
import java.util.AbstractMap.SimpleImmutableEntry

import scala.collection.JavaConversions._

import com.github.jknack.handlebars.ValueResolver
import com.github.jknack.handlebars.ValueResolver._

import org.json4s._
import org.json4s.JValue
import org.json4s.jackson.JsonMethods._

class JValueResolver extends ValueResolver {

  def resolve(context: AnyRef, name: String): AnyRef = context match {
    case JArray(array) =>
      array.lift(name.toInt) getOrElse UNRESOLVED
    case json: JValue =>
      (json \ name) match {
        case JNothing => UNRESOLVED
        case _ => resolve(json \ name)
      }
    case _ => UNRESOLVED
  }

  def resolve(context: Any): AnyRef = context match {
    case c: JValue =>
      c match {
        case JNothing => null
        case JNull => null
        case JString(s) => s
        case JDouble(num) => Double.box(num)
        case JDecimal(num) => num
        case JInt(num) => num
        case JBool(bool) => Boolean.box(bool)
        case JObject(obj) => obj
        case JArray(array) => array
      }
    case _ => throw new ClassCastException
  }

  private def unpack(jfield: (String, JValue)): Entry[String, AnyRef] =
    new SimpleImmutableEntry(jfield._1, resolve(jfield._2, jfield._1))

  def propertySet(context: AnyRef): JSet[Entry[String, AnyRef]] = context match {
    case JObject(obj) =>
      obj map unpack toSet: Set[Entry[String, AnyRef]]
    case _ => Set.empty[Entry[String, AnyRef]]
  }
}

object JValueResolver {
  val INSTANCE = new JValueResolver()
}
