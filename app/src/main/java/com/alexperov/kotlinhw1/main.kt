package com.alexperov.kotlinhw1

import java.math.BigDecimal
import java.math.RoundingMode


fun main() {
    println(transaction(BigDecimal(490)))
    println(transaction(BigDecimal(1234)))
    println(like(11))
    println(like(1))
    println(discount(BigDecimal(15000), false))
}

// 1.Task
fun transaction(amount: BigDecimal): String {
    val minTax = BigDecimal(35)
    val taxRate = BigDecimal(0.075)
    val tax = amount.multiply(taxRate).setScale(2, RoundingMode.DOWN)
    val result = if (tax > minTax) tax else minTax
    return "Комиссия за перевод составит ${result} руб."
}

//2.Task
fun like(likes: Int): String {
    val result =
        if (likes % 10 == 1 && likes != 11) "Понравилось $likes человеку" else "Понравилось $likes людям"
    return result
}

//3.Task

fun discount(amount: BigDecimal, regular: Boolean): BigDecimal {
    var minDisc = BigDecimal(100)
    var maxDisc = BigDecimal(0.05)
    var bonusDisc = BigDecimal(0.01)
    return when {
        !regular -> when {
            amount <= BigDecimal(1000) -> amount
            amount >= BigDecimal(1001) && amount <= BigDecimal(10000) -> amount - minDisc
            else -> amount - amount.multiply(maxDisc)
        }

        else -> when {
            amount <= BigDecimal(1000) -> amount - amount.multiply(bonusDisc)
            amount >= BigDecimal(1001) && amount <= BigDecimal(10000) -> amount - amount.multiply(
                bonusDisc
            ) - minDisc

            else -> amount - (amount.multiply(maxDisc) + amount.multiply(bonusDisc))
        }
    }.setScale(2, RoundingMode.HALF_UP)

}

