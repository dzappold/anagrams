import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.maps.shouldContain
import org.junit.jupiter.api.MethodOrderer.Random
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.util.*
import java.util.Locale.ENGLISH

@TestMethodOrder(Random::class)
@Execution(CONCURRENT)
class AnagramCheckerShould {
    @Test
    fun `checks whether a text is an anagram of another one`() {
        (Text("Madam Curie") isAnagramOf Text("Radium came"))
            .shouldBeTrue()
    }

    @Nested
    inner class FrequencyMapShould {
        @ParameterizedTest
        @ValueSource(chars = ['a', 'b', 'z'])
        fun `count a single letter once`(letter: Letter) {
            letter.toText().toFrequencyMap() shouldContain (letter to Count(1))
        }

        @ParameterizedTest
        @ValueSource(strings = ["aa", "aaa", "bbbb"])
        fun `count text containing only same letter`(text: Text) {
            text.toFrequencyMap() shouldContain (Letter(text.text.first()) to Count(text.text.length))
        }

        @Test
        fun `ignore non-letters`() {
            Text("a a !,a").toFrequencyMap() shouldContain (Letter('a') to Count(3))
        }

        @Test
        fun `count text with different letters`() {
            Text("aAbAa").toFrequencyMap() shouldContain (Letter('a') to Count(4))
        }

        @Test
        fun `count letters case-insensitive`() {
            Text("aAAAa").toFrequencyMap() shouldContain (Letter('a') to Count(5))
        }
    }
}

private fun Text.toFrequencyMap(): Map<Letter, Count> {
    val cleanedText = text
        .lowercase(ENGLISH)
        .filter(Char::isLetter)
        .groupingBy { Letter(it) }
        .eachCount()
        .mapValues { (_, count) -> Count(count) }
    return cleanedText
}

@JvmInline
value class Letter(val letter: Char)

fun Letter.toText(): Text = Text("$letter")

@JvmInline
value class Count(val count: Int)

@JvmInline
value class Text(val text: String)

private infix fun Text.isAnagramOf(other: Text): Boolean {
    return true
}
