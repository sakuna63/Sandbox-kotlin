import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals

object ExampleSpec : Spek({
    describe("sample description") {
        it("should be pass") {
            "🎃"
        }

        on("sample on") {
            var value = 1

            it("should be one") {
                assertEquals(expected = 1, actual = value)
            }

            value += 1

            it("should be two") {
                assertEquals(expected = 2, actual = value)
            }
        }
    }
})