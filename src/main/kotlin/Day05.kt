package org.example

import java.io.File

fun main() {
    val ranges = readRanges()
    val ids = readIds()

    var partOne = 0
    ids.forEach { id ->
        if (ranges.any { range -> id in range }) {
            partOne++
        }
    }

    val partTwo = mutableSetOf<LongRange>()
    ranges.forEach { range ->
        if (ids.any { id -> id in range }) {
            partTwo.add(range)
        }
    }

    partTwo
    println(partOne)
    print(mergeOverlapping(partTwo).sumOf { it.last - it.first + 1 })
}

private fun mergeOverlapping(ranges: MutableSet<LongRange>): Set<LongRange> {
    val sorted = ranges.sortedBy { it.first }
    val result = mutableListOf<LongRange>()

    var currentStart = sorted.first().first
    var currentEnd = sorted.first().last

    for (range in sorted.drop(1)) {
        if (range.first <= currentEnd + 1) {
            currentEnd = maxOf(currentEnd, range.last)
        } else {
            result.add(currentStart..currentEnd)
            currentStart = range.first
            currentEnd = range.last
        }
    }
    result.add(currentStart..currentEnd)
    return result.toSet()
}

private fun readRanges(): List<LongRange> =
    File("day05")
        .readLines()
        .takeWhile { it.isNotBlank() }
        .map { line ->
            line.trim()
                .split("-")
                .map(String::toLong)
                .let { (start, end) -> start..end }
        }

private fun readIds(): List<Long> =
    File("day05")
        .readLines()
        .dropWhile { it.isNotBlank() }
        .drop(1)
        .filter { it.isNotBlank() }
        .map(String::toLong)