package com.hdh.backpacker_task.ui.main.fragment

import com.hdh.backpacker_task.data.model.data.Location
import com.hdh.backpacker_task.ui.base.BaseView


interface MainFragmentView : BaseView {
    fun setRecyclerView(list : List<Location>)
    fun onError()
}