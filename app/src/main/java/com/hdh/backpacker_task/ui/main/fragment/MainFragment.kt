package com.hdh.backpacker_task.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hdh.backpacker_task.R
import com.hdh.backpacker_task.ui.base.mvp.MvpFragment

class MainFragment : MvpFragment<MainFragmentPresenter>(), MainFragmentView {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = super.setContentView(inflater, R.layout.fragment_main)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStatusBarResID(R.color.colorStatusBar)

        setOnClickListener()
    }

    private fun setOnClickListener() {

    }

    override fun createPresenter(): MainFragmentPresenter = MainFragmentPresenter(this)
}
