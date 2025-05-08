package day01

import readInput
import kotlin.math.absoluteValue

fun main() {
    fun part1(input: List<String>): Int {
        val parsed = input.map {
            val split = it.split("   ")
            val left = split[0].toInt()
            val right = split[1].toInt()
            Pair(left, right)
        }
        val left = parsed.map { it.first }.sorted()
        val right = parsed.map { it.second }.sorted()
        var distance = 0

        left.forEachIndexed { index, i ->
            distance += (i - right[index]).absoluteValue
        }
        return distance
    }

    fun part2(input: List<String>): Int {
        val parsed = input.map {
            val split = it.split("   ")
            val left = split[0].toInt()
            val right = split[1].toInt()
            Pair(left, right)
        }
        val left = parsed.map { it.first }
        val right = parsed.groupBy { it.second }.mapValues { it.value.size }

        var distance = 0

        left.forEach {
            distance += it * right.getOrDefault(it, 0)
        }
        return distance
    }

    // Part 1
    val testInput = readInput("day01/test")
    val puzzleInput = readInput("day01/input")

    run {
        println("Part 1")
        val testResult = part1(testInput)
        check(testResult == 11)

        val puzzleResult = part1(puzzleInput)
        println(puzzleResult)
    }

    // Part 2
    run {
        println("Part 2")
        val testResult = part2(testInput)
        check(testResult == 31)

        val puzzleResult = part2(puzzleInput)
        println(puzzleResult)
    }
}
