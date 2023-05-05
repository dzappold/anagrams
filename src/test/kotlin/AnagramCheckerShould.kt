import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.maps.shouldContain
import org.junit.jupiter.api.MethodOrderer.Random
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT

@TestMethodOrder(Random::class)
@Execution(CONCURRENT)
class AnagramCheckerShould {
    @Test
    fun `checks whether a text is an anagram of another one`() {
        (Text("Madam Curie") isAnagramOf Text("Radium came"))
            .shouldBeTrue()
    }

    @Nested
    class FrequencyMapShould {
        @Test
        fun `count a single letter once`() {
            Text("a").toFrequencyMap() shouldContain (Letter('a') to Count(1))
        }
    }
}

private fun Text.toFrequencyMap(): Map<Letter, Count> {
    TODO("Not yet implemented")
}

@JvmInline
value class Letter(val letter: Char)

@JvmInline
value class Count(val count: Int)

@JvmInline
value class Text(val text: String)

private infix fun Text.isAnagramOf(other: Text): Boolean {
    return true
}
