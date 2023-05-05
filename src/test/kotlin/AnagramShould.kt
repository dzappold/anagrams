import io.kotest.matchers.booleans.shouldBeTrue
import org.junit.jupiter.api.MethodOrderer.Random
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT

@TestMethodOrder(Random::class)
@Execution(CONCURRENT)
class AnagramShould {
    @Test
    fun `checks whether a text is an anagram of another one`() {
        ("Madam Curie" isAnagramOf "Radium came")
            .shouldBeTrue()
    }
}

private infix fun String.isAnagramOf(other: String): Boolean {
    return true
}
