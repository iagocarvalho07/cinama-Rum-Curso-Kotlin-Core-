package cinema

fun main() {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val rowSeats = readln().toInt()

    val cinema = MutableList(rows) { MutableList(rowSeats) { "S" } }
    val totalSeats = rows * rowSeats
    var purchasedSeats = 0
    var currentIncome = 0

    fun printCinema() {
        print("\nCinema:\n  ")
        for (i in 1..rowSeats) {
            print("$i ")
        }
        println()
        for (i in cinema.indices) {
            print("${i+1} ")
            for (j in cinema[i].indices) {
                print(cinema[i][j] + " ")
            }
            println()
        }
    }

    fun calculateTicketPrice(row: Int): Int {
        return if (totalSeats <= 60 || row <= rows / 2) {
            10
        } else {
            8
        }
    }

    fun buyTicket() {
        println("\nEnter a row number:")
        val selectedRow = readln().toInt() - 1
        println("Enter a seat number in that row:")
        val selectedSeat = readln().toInt() - 1

        if (selectedRow < 0 || selectedRow >= rows || selectedSeat < 0 || selectedSeat >= rowSeats) {
            println("\nWrong input!")
            buyTicket()
        } else if (cinema[selectedRow][selectedSeat] == "B") {
            println("\nThat ticket has already been purchased!")
            buyTicket()
        } else {
            val ticketPrice = calculateTicketPrice(selectedRow+1)
            cinema[selectedRow][selectedSeat] = "B"
            purchasedSeats++
            currentIncome += ticketPrice
            println("\nTicket price: \$$ticketPrice")
            printCinema()
        }
    }

    fun showStatistics() {
        val percentage: Double = purchasedSeats.toDouble() / totalSeats * 100
        val formatPercentage = "%.2f".format(percentage)
        val firstHalf = rows / 2
        val secondHalf = rows - firstHalf
        val ticketPrices = mutableListOf<Int>()
        repeat(firstHalf * rowSeats) { ticketPrices.add(10) }
        repeat(secondHalf * rows) { ticketPrices.add(8) }
        val sum = ticketPrices.sum()

        println("\nNumber of purchased tickets: $purchasedSeats")
        println("Percentage: $formatPercentage%")
        println("Current income: \$$currentIncome")
        println("Total income: \$$sum")
    }

    fun menu() {
        do {
            println("""
                1. Show the seats
                2. Buy a ticket
                3. Statistics
                0. Exit
            """.trimIndent())
            val menuOption = readln().toInt()
            when (menuOption) {
                1 -> printCinema()
                2 -> buyTicket()
                3 -> showStatistics()
                0 -> {}
            }
        } while (menuOption != 0)
    }

    menu()
}
