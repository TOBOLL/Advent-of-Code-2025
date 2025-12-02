package org.example

import java.io.File

fun main() {
    var currentNumber = 50
    var resultPartOne = 0
    var resultPartTwo = 0
    val data = readInput()

    data.forEach { line ->
        val direction = line.first().let { if (it == 'R') 1 else -1 }
        val value = line.drop(1).toInt()

        repeat(value) {
            currentNumber += direction
            currentNumber %= 100
            if (currentNumber == 0) {
                resultPartTwo++
            }
        }
        if (currentNumber == 0) {
            resultPartOne++
        }
    }
    println(resultPartOne)
    println(resultPartTwo)
}

private fun readInput(): List<String> = File("day01").readLines()