package day02

import readInput
import kotlin.math.absoluteValue

fun main() {
    fun List<String>.parse() = map { it.split(" ").map { it.toInt() } }
    fun List<Int>.isSafe(): Boolean {
        if (this != this.sorted() && this != this.sortedDescending()) return false
        for (i in 0 until this.size - 1) {
            val diff = (this[i] - this[i + 1]).absoluteValue
            if (diff > 3 || diff == 0) {
                return false
            }
        }
        return true
    }

    fun List<Int>.isSafeWithSingleTolerance(): Boolean {
        if(this.isSafe()) return true

        for(i in this.indices) {
            val sublist = this.toMutableList()
            sublist.removeAt(i)
            if (sublist.isSafe()) {
                return true
            }
        }

        return false
    }

    fun part1(input: List<String>): Int {
        val matches = input.parse().filter { it.isSafe() }
        return matches.count()
    }

    fun part2(input: List<String>): Int {
        val matches = input.parse().filter { it.isSafeWithSingleTolerance() }
        return matches.count()
    }


    // Part 1
    val testInput = readInput("day02/test")
    val puzzleInput = readInput("day02/input")

    run {
        println("Part 1")
        val testResult = part1(testInput)
        check(testResult == 2)

        val puzzleResult = part1(puzzleInput)
        println(puzzleResult)
    }

    // Part 2
    run {
        println("Part 2")
        val testResult = part2(testInput)
        check(testResult == 4)

        val puzzleResult = part2(puzzleInput)
        println(puzzleResult)
    }
}
