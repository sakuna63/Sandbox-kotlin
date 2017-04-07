import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.intellij.lang.annotations.Language

/**
 * Created by sakuna63 on 2017/03/15.
 */

fun main(args: Array<String>) {

    val mapper = ObjectMapper()
            .registerModule(KotlinModule())

    @Language("JSON")
    val json1 = """
      {
        "name": "taro",
        "gender": "MENS"
      }
"""
    deserialize(json1, mapper) // -> Person(name=taro, gender=Gender(label='メンズ',name='MENS'))

    @Language("JSON")
    val json2 = """
      {
        "name": "taro",
        "gender": 1
      }
"""
    deserialize(json2, mapper) // -> Person(name=taro, gender=Gender(label='Default',name='LADIES'))

    @Language("JSON")
    val json3 = """
      {
        "name": "taro",
        "gender": "M"
      }
"""
    deserialize2(json3, mapper) // -> Person(name=taro, gender=Gender2(raw='Default', label='メンズ', name='MENS'))
}

private fun deserialize(json: String, mapper: ObjectMapper) {
    val person = mapper.readValue(json, Person::class.java)
    println(person)
}

private fun deserialize2(json: String, mapper: ObjectMapper) {
    val person = mapper.readValue(json, Person2::class.java)
    println(person)
}

data class Person(val name: String, val gender: Gender)

enum class Gender(val label: String = "Default") {
    MENS("メンズ"),
    LADIES,
    NONE;

    override fun toString(): String {
        return "Gender(label='$label',name='$name')"
    }
}

data class Person2(val name: String, val gender: Gender2)

enum class Gender2(
        @get:JsonValue
        val raw: String,
        val label: String = "Default") {
    MENS("M", "メンズ"),
    LADIES("L"),
    NONE("N");

    override fun toString(): String {
        return "Gender2(raw='$raw', label='$label', name='$name')"
    }
}

