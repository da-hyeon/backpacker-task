package com.hdh.backpacker_task.ui.base

import android.content.Context

interface BaseView {
        var mContext : Context
        var mActivity : BaseActivity
        fun pushFragment(fragment: BaseFragment)
        fun pushMainFragment(fragment: BaseFragment)
        fun popFragment()

        fun onReturn()
        fun showLoading()
        fun hideLoading()
        fun showToast(message: String?)
}
