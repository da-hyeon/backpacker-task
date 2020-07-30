package com.hdh.backpacker_task.ui.base

import android.content.Context

interface BaseView {
        var mContext : Context
        var mActivity : BaseActivity
        fun pushFragment(fragment: BaseFragment)
        fun pushUpFragment(fragment: BaseFragment)
        fun pushMainFragment(fragment: BaseFragment)
        fun popFragment()
        fun popFragment(fragment: BaseFragment)

        fun setStatusBarNoAnimation(hex: Int)
        fun setStatusBarResID(id: Int)

        fun onReturn()
        fun showLoading()
        fun hideLoading()
        fun showToast(message: String?)

        fun hideKeyboard()
}
