package com.hdh.kakao_pay_task.utils

import android.app.Activity
import android.util.DisplayMetrics

object DPIUtil {
    private val metrics: DisplayMetrics by lazy {
        DisplayMetrics()
    }

    fun init(activity: Activity) {
        activity.windowManager.defaultDisplay.getMetrics(metrics)
    }

    fun dp2px(dp: Float): Float {
        return metrics.density.times(dp)
    }

    fun px2dp(px: Float): Float {
        return px.div(metrics.density)
    }
}