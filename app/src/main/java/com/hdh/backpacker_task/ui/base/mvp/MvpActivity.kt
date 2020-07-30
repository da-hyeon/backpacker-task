package com.hdh.backpacker_task.ui.base.mvp

import android.os.Bundle
import com.hdh.backpacker_task.ui.base.BaseActivity
import com.hdh.backpacker_task.ui.base.BasePresenter

abstract class MvpActivity<P : BasePresenter<*>> : BaseActivity() {

    protected lateinit var mvpPresenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        mvpPresenter = createPresenter()
        super.onCreate(savedInstanceState)
    }

    protected abstract fun createPresenter(): P

    override fun onDestroy() {
        super.onDestroy()
        mvpPresenter.onDetach()
    }
}