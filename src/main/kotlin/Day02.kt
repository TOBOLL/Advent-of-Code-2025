package org.example

import java.io.File

fun main() {
    var resultPartOne = 0L
    var resultPartTwo = 0L
    val data = readInput()

    data.forEach {
        it.first()
        it.second()
        for (i in it.first().toLong() .. it.second().toLong()) {
            if (isSplitNumberEqualsEachOther(i)) {
                resultPartOne += i
            }
            if (isRepeatingNumber(i)) {
                resultPartTwo += i
            }
        }

    }

    println(resultPartOne)
    println(resultPartTwo)
}

private fun readInput() = File("day02").readLines()
    .flatMap { lines ->
        lines.split(",")
            .filter { it.isNotEmpty() }
            .map { it.split("-") }
    }

private fun List<String>.second() = this[1]

fun isSplitNumberEqualsEachOther(num: Long): Boolean =
    num.toString().let { s ->
        s.takeIf { it.length % 2 == 0 }
            ?.chunked(s.length / 2)
            ?.let { it[0] == it[1] } == true
    }

fun isRepeatingNumber(num: Long): Boolean =
    num.toString().let { s ->
        (1..s.length / 2)
            .filter { s.length % it == 0 }
            .map { s.substring(0, it) }
            .any { pattern -> s == pattern.repeat(s.length / pattern.length) }
    }