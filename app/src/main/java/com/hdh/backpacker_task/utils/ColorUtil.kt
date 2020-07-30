package com.hdh.kakao_pay_task.utils

object ColorUtil {
    fun fadeColor(start: Int, end: Int, ratio: Float): Int {
        val sr = start and 0xff0000 shr 16
        val sg = start and 0x00ff00 shr 8
        val sb = start and 0x0000ff
        val dr = end and 0xff0000 shr 16
        val dg = end and 0x00ff00 shr 8
        val db = end and 0x0000ff
        val r = ((1 - ratio) * sr + ratio * dr).toInt()
        val g = ((1 - ratio) * sg + ratio * dg).toInt()
        val b = ((1 - ratio) * sb + ratio * db).toInt()
        return -0x1000000 or (r shl 16) or (g shl 8) or b
    }
}