import java.util.Locale

internal fun Text.toFrequencyMap(): Map<Letter, Count> =
    text
        .lowercase(Locale.ENGLISH)
        .filter(Char::isLetter)
        .groupingBy { Letter(it) }
        .eachCount()
        .mapValues { (_, count) -> Count(count) }

@JvmInline
value class Letter(val letter: Char)


@JvmInline
value class Count(val count: Int)

@JvmInline
value class Text(val text: String)

infix fun Text.isAnagramOf(other: Text): Boolean =
    toFrequencyMap() == other.toFrequencyMap()