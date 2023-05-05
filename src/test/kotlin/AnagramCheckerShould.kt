import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.maps.shouldContain
import io.kotest.matchers.maps.shouldContainExactly
import org.junit.jupiter.api.MethodOrderer.Random
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
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

    @Test
    fun `checks two texts with different letters can't be an anagram`() {
        (Text("abc") isAnagramOf Text("xyz"))
            .shouldBeFalse()
    }

    @Test
    fun `checks two texts with different amount of a letter can't be an anagram`() {
        (Text("llll") isAnagramOf Text("llllll"))
            .shouldBeFalse()
    }

    @ParameterizedTest
    @MethodSource("wikipediaExamples")
    fun `wikipedia examples`(first: Text, second: Text) {
        first.isAnagramOf(second).shouldBeTrue()
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
            Text("aabaa").toFrequencyMap() shouldContainExactly
                    mapOf(
                        Letter('a') to Count(4),
                        Letter('b') to Count(1)
                    )
        }

        @Test
        fun `count letters case-insensitive`() {
            Text("aAAAa").toFrequencyMap() shouldContain (Letter('a') to Count(5))
        }
    }

    companion object {
        @JvmStatic
        fun wikipediaExamples(): List<Arguments> =
            listOf(
                arguments("New York Times", "monkeys write"),
                arguments("Church of Scientology", "rich-chosen goofy cult"),
                arguments("McDonald's restaurants", "Uncle Sam's standard rot"),
                arguments("coronavirus", "carnivorous"),
                arguments("She Sells Sanctuary", "Santa; shy, less cruel"),
                arguments("She Sells Sanctuary", "Satan; cruel, less shy"),
                arguments("evil", "vile"),
                arguments("a gentleman", "elegant man"),
                arguments("eleven plus two", "twelve plus one"),
                arguments("restful", "fluster"),
                arguments("cheater", "teacher"),
                arguments("funeral", "real fun"),
                arguments("adultery", "true lady"),
                arguments("forty five", "over fifty"),
                arguments("Santa", "Satan"),
                arguments("William Shakespeare", "I am a weakish speller"),
                arguments("Madam Curie", "Radium came"),
                arguments("George Bush", "He bugs Gore"),
                arguments("Tom Marvolo Riddle", "I am Lord Voldemort"),
                arguments("silent", "listen"),
                arguments("Anagrams", "Ars magna"),
            )
    }
}

private fun Text.toFrequencyMap(): Map<Letter, Count> =
    text
        .lowercase(ENGLISH)
        .filter(Char::isLetter)
        .groupingBy { Letter(it) }
        .eachCount()
        .mapValues { (_, count) -> Count(count) }

@JvmInline
value class Letter(val letter: Char)

fun Letter.toText(): Text = Text("$letter")

@JvmInline
value class Count(val count: Int)

@JvmInline
value class Text(val text: String)

private infix fun Text.isAnagramOf(other: Text): Boolean =
    toFrequencyMap() == other.toFrequencyMap()
