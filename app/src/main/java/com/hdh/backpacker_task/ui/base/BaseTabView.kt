package com.hdh.backpacker_task.ui.base

interface BaseTabView : BaseView {
    override fun popFragment() {}
    override fun popFragment(fragment: BaseFragment) {}
    override fun onReturn() {}
}
