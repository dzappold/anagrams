import java.util.Locale.ENGLISH

@JvmInline
value class Text(val text: String)

infix fun Text.isAnagramOf(other: Text): Boolean =
    toFrequencyMap() == other.toFrequencyMap()

internal fun Text.toFrequencyMap(): Map<Letter, Count> =
    text
        .lowercase(ENGLISH)
        .filter(Char::isLetter)
        .groupingBy(::Letter)
        .eachCount()
        .mapValues { (_, count) -> Count(count) }

@JvmInline
internal value class Letter(val letter: Char)

@JvmInline
internal value class Count(val count: Int)
