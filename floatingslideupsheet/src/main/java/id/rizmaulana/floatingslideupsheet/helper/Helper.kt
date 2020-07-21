package id.rizmaulana.floatingslideupsheet.helper

/**
 * rizmaulana 21/07/20.
 */

fun setColorAlpha(percentage: Int): String {
    val decValue = percentage.toDouble() / 100 * 255
    val rawHexColor = "#000000".replace("#", "")
    val str = StringBuilder(rawHexColor)
    if (Integer.toHexString(decValue.toInt()).length == 1) str.insert(
        0,
        "#0" + Integer.toHexString(decValue.toInt())
    ) else str.insert(0, "#" + Integer.toHexString(decValue.toInt()))
    return str.toString()
}
