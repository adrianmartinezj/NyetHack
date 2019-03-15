fun main(args: Array<String>) {
//    var beverage = readLine()?.let {
//        if(it.isNotBlank()){
//            it.capitalize()
//        } else {
//            "Buttered Ale"
//        }
//    }
//    var beverage = readLine()!!.capitalize()
    var beverage = readLine()
    // beverage = null
    if (beverage != null){
        beverage = beverage.capitalize()
    } else {
        println("I can't do that without crashing - beverage was null!")
    }

//    println(beverage)
    val beverageServed:String = beverage ?: "Buttered Ale"
    println(beverageServed)
}