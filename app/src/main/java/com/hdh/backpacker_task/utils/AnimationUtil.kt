package com.hdh.kakao_pay_task.utils

import android.view.View
import android.view.animation.AlphaAnimation

object AnimationUtil {
    fun setAlphaAnimation(view : View){
        val alphaAnimation = AlphaAnimation(0.0f,1.0f)
        alphaAnimation.fillAfter = true
        alphaAnimation.duration = 250
        view.startAnimation(alphaAnimation)
    }
}