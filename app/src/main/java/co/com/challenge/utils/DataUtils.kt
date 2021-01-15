package co.com.challenge.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

/**
 * Created by Elkin Fracica on 1/14/21.
 */


fun toPrice(price: Float): String = toPrice(String.format("%.0f", price))

fun toPrice(text: String?): String {
    if (text.isNullOrEmpty()) return "$ 0"
    val sym = DecimalFormatSymbols.getInstance()
    sym.groupingSeparator = '.'
    return try {
        DecimalFormat("$ ###,###", sym).format(text.replace(",", ".").toFloat())
    } catch (e: Exception) {
        ""
    }
}