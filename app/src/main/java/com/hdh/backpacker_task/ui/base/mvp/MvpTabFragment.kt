package com.hdh.backpacker_task.ui.base.mvp

import android.os.Bundle
import android.view.View
import com.hdh.backpacker_task.ui.base.BasePresenter
import com.hdh.backpacker_task.ui.base.BaseTabFragment

abstract class MvpTabFragment<P : BasePresenter<*>> : BaseTabFragment() {

    protected lateinit var mPresenter: P

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter = createPresenter()
    }

    protected abstract fun createPresenter(): P

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter.onDetach()
    }
}
