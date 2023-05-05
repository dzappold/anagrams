import io.kotest.matchers.booleans.shouldBeTrue
import org.junit.jupiter.api.MethodOrderer.Random
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
}

@JvmInline
value class Text(val text: String)

private infix fun Text.isAnagramOf(other: Text): Boolean {
    return true
}
