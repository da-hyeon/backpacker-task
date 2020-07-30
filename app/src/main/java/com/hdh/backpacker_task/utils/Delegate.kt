package com.hdh.kakao_pay_task.utils

class Delegate {
    interface Callback<T> {
        fun run(t: T)
    }
}