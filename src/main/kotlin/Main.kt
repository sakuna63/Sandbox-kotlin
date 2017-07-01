import com.squareup.moshi.*
import java.lang.reflect.Type
import java.util.*


fun main(args: Array<String>) {
    println("test")

    serialize()
//    deserialize()
}

private fun serialize() {
    val blackjackHand = BlackjackHand(
            hiddenCard = Card('6', null),
            visibleCards = emptyList())

    val moshi = Moshi.Builder()
            .add(SuitAdapter)
            .add(KotlinJsonAdapterFactory())
            .build()
    val type: Type = Types.newParameterizedType(BlackjackHand::class.java, Card::class.java)
    val jsonAdapter: JsonAdapter<BlackjackHand<Card>> = moshi.adapter(type)

    val json = jsonAdapter.toJson(blackjackHand)
    println(json)
}

private fun deserialize() {
    val json = """
      {
        "hidden_card" : {
          "rank" : "6",
          "suit" : 4
        },
        "visible_cards" : [
          {
            "rank" : "4",
            "suit" : 1
          },
          {
            "rank" : "A",
            "suit" : 3
          }
        ]
      }
    """

    val moshi = Moshi.Builder()
            .add(SuitAdapter)
            .add(KotlinJsonAdapterFactory())
            .build()
    val type: Type = Types.newParameterizedType(BlackjackHand::class.java, Card::class.java)
    val jsonAdapter: JsonAdapter<BlackjackHand<Card>> = moshi.adapter(type)

    val blackjackHand: BlackjackHand<Card>? = jsonAdapter.fromJson(json)
    println(blackjackHand)
    val card = blackjackHand?.visibleCards?.first()
    card?.let {
        println(it.suit?.name)
    }
}

data class BlackjackHand<T>(
        @Json(name = "hidden_card")
        @field:Json(name = "hidden_card")
        val hiddenCard: T,
        @Json(name = "visible_cards")
        @field:Json(name = "visible_cards")
        val visibleCards: List<T>
)

data class Card(
        @Json(name = "rank")
        @field:Json(name = "rank")
        val rank: Char,
        @Json(name = "suit")
        @field:Json(name = "suit")
        val suit: Suit? = null
)

enum class Suit(val raw: Int) {
    CLUBS(1),
    DIAMONDS(2),
    HEARTS(3),
    SPADES(4);
}

object SuitAdapter {
    @ToJson
    fun serialize(suit: Suit): Int = suit.raw

    @FromJson
    fun deserialize(raw: Int) = Suit.values().find { it.raw == raw }
}
