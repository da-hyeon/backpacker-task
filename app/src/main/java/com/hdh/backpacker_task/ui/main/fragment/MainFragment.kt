package com.hdh.backpacker_task.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.hdh.backpacker_task.R
import com.hdh.backpacker_task.data.model.data.Location
import com.hdh.backpacker_task.ui.base.mvp.MvpFragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : MvpFragment<MainFragmentPresenter>(), MainFragmentView,
    SwipeRefreshLayout.OnRefreshListener {

    private val mLocalWeatherListAdapter by lazy {
        LocalWeatherListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = super.setContentView(inflater, R.layout.fragment_main)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refresh_swipe.setOnRefreshListener(this)
        refresh_swipe.isEnabled = false
        recycler_local_weather.adapter = mLocalWeatherListAdapter
    }

    override fun createPresenter(): MainFragmentPresenter = MainFragmentPresenter(this)

    override fun setRecyclerView(list: List<Location>) {
        recycler_local_weather.visibility = View.VISIBLE
        mLocalWeatherListAdapter.items = list
        refresh_swipe.isEnabled = true
    }

    override fun onError() {
        refresh_swipe.isEnabled = true
    }

    override fun onRefresh() {
        recycler_local_weather.visibility = View.GONE
        refresh_swipe.isRefreshing = false
        refresh_swipe.isEnabled = false
        mPresenter.sendReSearch()
    }
}
