package day04

import readInput

fun main() {
    fun List<String>.parse() = map { it.toList() }
    fun List<List<Char>>.findXmasAt(row: Int, col: Int): Int {
        var xmasCount = 0
        if (this[row][col] != 'X') return xmasCount
        val rows = this.size
        val cols = this[row].size

        // Up
        if (row >= 3) {
            if (this[row - 1][col] == 'M' && this[row - 2][col] == 'A' && this[row - 3][col] == 'S') {
                xmasCount++
            }
        }
        // Down
        if (row <= rows - 4) {
            if (this[row + 1][col] == 'M' && this[row + 2][col] == 'A' && this[row + 3][col] == 'S') {
                xmasCount++
            }
        }
        // Left
        if (col >= 3) {
            if (this[row][col - 1] == 'M' && this[row][col - 2] == 'A' && this[row][col - 3] == 'S') {
                xmasCount++
            }
        }
        // Right
        if (col <= cols - 4) {
            if (this[row][col + 1] == 'M' && this[row][col + 2] == 'A' && this[row][col + 3] == 'S') {
                xmasCount++
            }
        }
        // Up Left
        if (row >= 3 && col >= 3) {
            if (this[row - 1][col - 1] == 'M' && this[row - 2][col - 2] == 'A' && this[row - 3][col - 3] == 'S') {
                xmasCount++
            }
        }
        // Up Right
        if (row >= 3 && col <= cols - 4) {
            if (this[row - 1][col + 1] == 'M' && this[row - 2][col + 2] == 'A' && this[row - 3][col + 3] == 'S') {
                xmasCount++
            }
        }
        // Down Left
        if (row <= rows - 4 && col >= 3) {
            if (this[row + 1][col - 1] == 'M' && this[row + 2][col - 2] == 'A' && this[row + 3][col - 3] == 'S') {
                xmasCount++
            }
        }
        // Down Right
        if (row <= rows - 4 && col <= cols - 4) {
            if (this[row + 1][col + 1] == 'M' && this[row + 2][col + 2] == 'A' && this[row + 3][col + 3] == 'S') {
                xmasCount++
            }
        }
        return xmasCount
    }

    fun List<List<Char>>.findXShapedMasAt(row: Int, col: Int): Int {
        var xmasCount = 0

        if (this[row][col] != 'A') return xmasCount
        val rows = this.size
        val cols = this[row].size

        if (row == 0 || col == 0 || row == rows - 1 || col == cols - 1) return xmasCount

        // Up
        if (this[row - 1][col - 1] == 'M' && this[row - 1][col + 1] == 'M' && this[row + 1][col - 1] == 'S' && this[row + 1][col + 1] == 'S') {
            xmasCount++
        }
        // Down
        if (this[row + 1][col - 1] == 'M' && this[row + 1][col + 1] == 'M' && this[row - 1][col - 1] == 'S' && this[row - 1][col + 1] == 'S') {
            xmasCount++
        }
        // Left
        if (this[row - 1][col - 1] == 'M' && this[row + 1][col - 1] == 'M' && this[row - 1][col + 1] == 'S' && this[row + 1][col + 1] == 'S') {
            xmasCount++
        }
        // Right
        if (this[row - 1][col + 1] == 'M' && this[row + 1][col + 1] == 'M' && this[row - 1][col - 1] == 'S' && this[row + 1][col - 1] == 'S') {
            xmasCount++
        }
        return xmasCount
    }

    fun part1(input: List<String>): Int {
        val parsed = input.parse()
        var xmasCount = 0
        for (row in parsed.indices) {
            for (col in parsed[row].indices) {
                xmasCount += parsed.findXmasAt(row, col)
            }
        }
        return xmasCount
    }

    fun part2(input: List<String>): Int {
        val parsed = input.parse()
        var xmasCount = 0
        for (row in parsed.indices) {
            for (col in parsed[row].indices) {
                xmasCount += parsed.findXShapedMasAt(row, col)
            }
        }
        return xmasCount
    }


    // Part 1
    val testInput = readInput("day04/test")
    val puzzleInput = readInput("day04/input")

    run {
        println("Part 1")
        val testResult = part1(testInput)
        check(testResult == 18)

        val puzzleResult = part1(puzzleInput)
        println(puzzleResult)
    }

    // Part 2
    run {
        println("Part 2")
        val testResult = part2(testInput)
        check(testResult == 9)

        val puzzleResult = part2(puzzleInput)
        println(puzzleResult)
    }
}
