import kotlin.math.roundToInt
import java.io.File

const val TAVERN_NAME = "Taernyl's Folly"

var playerGold = 10
var playerSilver = 10

val patronList = mutableListOf("Eli", "Mordoc", "Sophie")
val menuList = File("data/tavern-menu-data.txt")
    .readText()
    .split("\n")

fun main(args: Array<String>) {
    if (patronList.contains("Eli")){
        println("The tavern master says: Eli's in the back playing cards.")
    } else {
        println("The tavern master says: Eli isn't here.")
    }

    if (patronList.containsAll(listOf("Sophie", "Mordoc"))) {
        println("The tavern master says: Yea, they're seated by the stew kettle.")
    } else {
        println("The tavern master says: Nay, they departed hours ago.")
    }

//    placeOrder("shandy,Dragon's Breath,5.91")
//    placeOrder("elixir,Shirley's Temple,4.12")

    patronList.forEachIndexed{ index, patron->
        println("Good evening, $patron - you're #${index + 1} in line.")
        placeOrder(patron, "shandy,Dragon's Breath, 5.91")
    }

    menuList.forEachIndexed{ index, data->
        println("$index: $data")
    }
}

fun performPurchase(price: Double){
    displayBalance()
    val totalPurse = playerGold + (playerSilver / 100.0)
    println("Total purse: $totalPurse")
    if (totalPurse > price) {
        println("Purchasing item for $price")

        val remainingBalance = totalPurse - price
        println("Remaining balance: ${"%.2f"}".format(remainingBalance))

        val remainingGold = remainingBalance.toInt()
        val remainingSilver = (remainingBalance % 1 * 100).roundToInt()
        playerGold = remainingGold
        playerSilver = remainingSilver
    } else {
        println("Not enough for transaction!")
    }
    displayBalance()



//    val dragonsBreathSize = 5.0
//    val pintSize = .125

//    println("After 12 pints are sold, there are ${dragonsBreathSize - (pintSize * 12)} gallons left!")
}

private fun displayBalance(){
    println("Player's purse balance: Gold: $playerGold, Silver: $playerSilver")
}

fun placeOrder(patronName: String, menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
//    println("Madrigal speaks with $tavernMaster about their order.")
    println("$patronName speaks with the $tavernMaster about their order.")

    val (type, name, price) = menuData.split(',')
    val message = "$patronName buys a $name ($type) for $price."
    println(message)

//    performPurchase(price.toDouble())

    val phrase = if (name == "Dragon's Breath") {
        "$patronName exclaims ${toDragonSpeak("IT'S GOT WHAT ADVENTURERS CRAVE!")}"
    } else {
        "$patronName says: Thanks for the $name"
    }
    println(phrase)
}

private fun toDragonSpeak(phrase:String) =
        phrase.replace(Regex("[AEIOUaeiou]")){
            when (it.value){
                "a","A" -> "4"
                "e","E" -> "3"
                "i","I" -> "1"
                "o","O" -> "0"
                "u","U" -> "|_|"
                else -> it.value
            }
        }