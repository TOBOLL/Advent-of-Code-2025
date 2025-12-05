package org.example

import java.io.File

private val data = readInput()
private val sizeOfRows = data.first().size
private val sizeOfColumns = data.size

fun main() {
    val resultPartOne = data.indices.sumOf { y ->
        data[y].indices.count { x ->
            checkIfThereIsRollsOfPaper(y, x) && data[y][x] == '@'
        }.toLong()
    }

    var resultPartTwo = 0L
    do {
        var rollsToRemove = 0
        for (y in data.indices) {
            for (x in data[y].indices) {
                if (checkIfThereIsRollsOfPaper(y, x) && data[y][x] == '@') {
                    data[y][x] = 'X'
                    rollsToRemove++
                }
            }
        }
        resultPartTwo += rollsToRemove
    } while (rollsToRemove > 0)

    println("part One: $resultPartOne")
    println("part One: $resultPartTwo")
}

private val directionsFromRightToLeftAndFromBottomToUp = listOf(
    // right
    Pair(0, 1),
    // left
    Pair(0, -1),
    // up
    Pair(1, 0),
    // down
    Pair(-1, 0),
    //diagonal
    Pair(1, 1),
    Pair(1, -1),
    Pair(-1, 1),
    Pair(-1, -1),
)

private fun readInput(): Array<Array<Char>> = File("day04").readLines()
    .map { line -> line.toCharArray().toTypedArray() }.toTypedArray()

private fun checkIfThereIsRollsOfPaper(y: Int, x: Int): Boolean {
    val rolls = directionsFromRightToLeftAndFromBottomToUp.count { (dy, dx) ->
        val currentY = y + dy
        val currentX = x + dx
        isInsideOfArray(currentY, currentX) && data[currentY][currentX] == '@'
    }
    return rolls < 4
}

private fun isInsideOfArray(y: Int, x: Int): Boolean = x in 0 until sizeOfRows && y in 0 until sizeOfColumns
