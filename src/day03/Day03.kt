package day03

import readInput
import kotlin.math.absoluteValue

fun main() {

    fun part1(input: List<String>): Long {
        val regex = "mul\\(([1-9][0-9]{0,2}),([1-9][0-9]{0,2})\\)".toRegex()
        val matches = regex.findAll(input.joinToString("\n"))
            .map { Pair(it.groupValues[1].toLong(), it.groupValues[2].toLong()) }
            .toList()
        return matches.sumOf { it.first * it.second }
    }

    fun part2(input: List<String>): Long {
        val regex = "mul\\(([1-9][0-9]{0,2}),([1-9][0-9]{0,2})\\)|don't\\(\\)|do\\(\\)".toRegex()
        val matches = regex.findAll(input.joinToString("\n"))
            .toList()

        var shouldDo = true
        var total = 0L
        matches.forEach {
            when {
                it.value == "don't()" -> shouldDo = false
                it.value == "do()" -> shouldDo = true
                shouldDo -> total += (it.groupValues[1].toLong() * it.groupValues[2].toLong())
            }
        }
        return total
    }

    // Part 1
    val testInput = readInput("day03/test")
    val puzzleInput = readInput("day03/input")

    run {
        println("Part 1")
        val testResult = part1(testInput)
        check(testResult == 161L)

        val puzzleResult = part1(puzzleInput)
        println(puzzleResult)
    }

    // Part 2
    run {
        println("Part 2")
        val testInput2 = readInput("day03/test2")
        val testResult = part2(testInput2)
        check(testResult == 48L)

        val puzzleResult = part2(puzzleInput)
        println(puzzleResult)
    }
}
