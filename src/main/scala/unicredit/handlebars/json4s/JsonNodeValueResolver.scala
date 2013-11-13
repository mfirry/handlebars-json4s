package unicredit.handlebars
package json4s

import java.util.Map.Entry
import java.util.{Set => JSet}

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
      if(resolved == JNothing) {
        UNRESOLVED
      } else {
        resolved
      }
    case _ =>
      UNRESOLVED
  }

  // def resolve(json: JValue): AnyRef = json match {
  //   case JArray(xs) => xs
  //   case JString(string) => string
  //   // case JObject(object) => object
  //   case JBool(bool) => bool
  //   case JDouble(double) => double
  //   case JDecimal(decimal) => decimal
  //   case JNull => null
  //   case JNothing => null
  // }

  def propertySet(context: AnyRef): JSet[Entry[String, AnyRef]] = context match {
    case JObject(obj) => ???
    case _ => Set.empty[Entry[String, AnyRef]]
  }
}