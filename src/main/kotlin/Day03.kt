package org.example

import java.io.File

fun main() {
    var resultPartOne = 0L
    var resultPartTwo = 0L
    val data = readInput()

    data.forEach { bank ->
        val firstDigit = bank.dropLast(1).max()
        val firstIndex = bank.indexOf(firstDigit)
        val secondDigit = bank.substring(firstIndex + 1).max()
        resultPartTwo += findLargestBank(bank)
        resultPartOne += firstDigit.digitToInt() * 10 + secondDigit.digitToInt()
    }
    println("part One: $resultPartOne")
    println("part One: $resultPartTwo")
}

private fun findLargestBank(bank: String): Long {
    var searchIndex = 0
    return buildString {
        repeat(12) { searchCycle ->
            val digit = bank.dropLast(11 - searchCycle).substring(searchIndex).max()
            searchIndex = bank.indexOf(digit, searchIndex) + 1
            append(digit)
        }
    }.toLong()
}

private fun readInput(): List<String> = File("day03").readLines()