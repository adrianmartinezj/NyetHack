import kotlin.math.roundToInt

const val TAVERN_NAME = "Taernyl's Folly"

var playerGold = 10
var playerSilver = 10

fun main(args: Array<String>) {
    placeOrder("shandy,Dragon's Breath,5.91")
//    placeOrder("elixir,Shirley's Temple,4.12")
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

fun placeOrder(menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("Madrigal speaks with $tavernMaster about their order.")

    val (type, name, price) = menuData.split(',')
    val message = "Madrigal buys a $name ($type) for $price."
    println(message)

    performPurchase(price.toDouble())

    val phrase = if (name == "Dragon's Breath") {
        "Madrigal exclaims ${toDragonSpeak("IT'S GOT WHAT ADVENTURERS CRAVE!")}"
    } else {
        "Madrigal says: Thanks for the $name"
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