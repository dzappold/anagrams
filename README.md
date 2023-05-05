# Anagrams

## Task

Write a Java program that checks if two texts are anagrams of each other.
Please use the english wikipedia entry for the definition of anagram.
The solution has to be in Java or Kotlin.
Feel free to use your favorite IDE, unit test frameworks, automated build system etc.
You can prioritize however you like (performance, readability, extensibility, …).
Googling is a good thing :)

### wikipedia description

[Definition of an anagram][Anagram@Wikipedia]

> An anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all
> the original letters exactly once.[1] For example, the word anagram itself can be rearranged into nag a ram, as well
> as
> the word binary into brainy and the word adobe into abode.
>
> The original word or phrase is known as the subject of the anagram. Any word or phrase that exactly reproduces the
> letters in another order is an anagram. Someone who creates anagrams may be called an "anagrammatist",[2] and the goal
> of a serious or skilled anagrammatist is to produce anagrams that reflect or comment on their subject.

#### Examples

Anagrams may be created as a commentary on the subject. They may be a parody, a criticism or satire. For example:

    "New York Times" = "monkeys write"
    "Church of Scientology" = "rich-chosen goofy cult"
    "McDonald's restaurants" = "Uncle Sam's standard rot"
    "coronavirus" = "carnivorous"
    "She Sells Sanctuary" = "Santa; shy, less cruel" or "Satan; cruel, less shy"

An anagram may also be a synonym of the original word. For example:

    "evil" = "vile"
    "a gentleman" = "elegant man"
    "eleven plus two" = "twelve plus one"

An anagram that has a meaning opposed to that of the original word or phrase is called an "antigram".[3] For example:

    "restful" = "fluster"
    "cheater" = "teacher"
    "funeral" = "real fun"
    "adultery" = "true lady"
    "forty five" = "over fifty"
    "Santa" = "Satan"

They can sometimes change from a proper noun or personal name into an appropriate sentence:

    "William Shakespeare" = "I am a weakish speller"
    "Madam Curie" = "Radium came"
    "George Bush" = "He bugs Gore"
    "Tom Marvolo Riddle" = "I am Lord Voldemort"

They can change part of speech, such as the adjective "silent" to the verb "listen".

"Anagrams" itself can be anagrammatized as "Ars magna" (Latin, 'the great art').

## Thoughts and Decisions

- Since we are talking about `texts` in the task description or about `word` or `phrase` in wikipedia - it might make
  sense to introduce a tiny type that represents the domain

## Frameworks used

- [result4k Library][result4k]
- [JUnit Testing Framework][junit]
- [Kotest Assertion Library][kotest]
- [Mockk Mocking Library][mockk]

[result4k]: https://github.com/fork-handles/forkhandles

[junit]: https://junit.org/junit5/

[kotest]: https://kotest.io

[mockk]: https://mockk.io

[Anagram@Wikipedia]: https://en.wikipedia.org/wiki/Anagram
