package com.hdh.backpacker_task.ui.main.activity

import android.os.Bundle
import android.view.View
import com.hdh.backpacker_task.R
import com.hdh.backpacker_task.ui.base.BaseActivity
import com.hdh.backpacker_task.ui.main.fragment.MainFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addSubscription(
            loadingState
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it) showLoading() else hideLoading()
                }
        )

        pushMainFragment(MainFragment())
        progress_bar.bringToFront()
    }

    private fun showLoading() {
        progress_bar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        progress_bar.visibility = View.GONE
    }
}