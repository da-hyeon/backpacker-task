package com.hdh.backpacker_task.ui.main.fragment

import com.hdh.backpacker_task.ui.base.BasePresenter


class MainFragmentPresenter() : BasePresenter<MainFragmentView>() {

    constructor(view: MainFragmentView) : this() {
        onAttach(view)
    }

}