package day05

import readInput

fun main() {
    fun List<String>.parse(): Pair<List<Pair<Int, Int>>, List<List<Int>>> {
        val sectionSplitIndex = this.indexOf("")
        val rulesSection = this.subList(0, sectionSplitIndex)
        val updateSection = this.subList(sectionSplitIndex + 1, this.size)
        return Pair(
            rulesSection.map {
                val split = it.split("|")
                Pair(split[0].toInt(), split[1].toInt())
            },
            updateSection.map { it.split(",").map { it.toInt() } }
        )
    }

    fun List<Int>.passesRules(rules: List<Pair<Int, Int>>): Boolean {
        rules.forEach { (first, second) ->
            if (this.contains(second) && this.indexOf(first) > this.indexOf(second)) {
                return false
            }
        }
        return true
    }

    fun List<Int>.correct(rules: List<Pair<Int, Int>>): List<Int> {
        val result = this.toMutableList()
        val relevantRules = rules.filter { (first, second) ->
            this.contains(first) && this.contains(second)
        }

        var shouldContinue = true
        while (shouldContinue) {
            shouldContinue = false
            relevantRules.forEach { (first, second) ->
                val indexFirst = result.indexOf(first)
                val indexSecond = result.indexOf(second)
                if (indexFirst > indexSecond) {
                    result[indexFirst] = second
                    result[indexSecond] = first
                    shouldContinue = true
                }
            }
        }
        return result
    }

    fun List<Int>.getMiddle(): Int {
        return this[this.size / 2]
    }

    fun part1(input: List<String>): Int {
        val (rules, updates) = input.parse()
        val (validUpdates, _) = updates.partition { it.passesRules(rules) }
        return validUpdates.sumOf { it.getMiddle() }
    }

    fun part2(input: List<String>): Int {
        val (rules, updates) = input.parse()
        val (_, invalidUpdates) = updates.partition { it.passesRules(rules) }
        val correctedUpdates = invalidUpdates.map { it.correct(rules) }
        return correctedUpdates.sumOf { it.getMiddle() }
    }


    // Part 1
    val testInput = readInput("day05/test")
    val puzzleInput = readInput("day05/input")

    run {
        println("Part 1")
        val testResult = part1(testInput)
        check(testResult == 143)

        val puzzleResult = part1(puzzleInput)
        println(puzzleResult)
    }

    // Part 2
    run {
        println("Part 2")
        val testResult = part2(testInput)
        check(testResult == 123)

        val puzzleResult = part2(puzzleInput)
        println(puzzleResult)
    }
}
