package com.hdh.backpacker_task.ui.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hdh.backpacker_task.data.ApiStores
import com.hdh.kakao_pay_task.utils.ClickUtil
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseTabFragment : Fragment(), BaseTabView {
    lateinit var mView: View
    override lateinit var mContext: Context
    override lateinit var mActivity: BaseActivity
    protected val compositeDisposable = CompositeDisposable()
    private var statusBarHexColor = 0
    private var statusBarResID = 0
    protected lateinit var click: ClickUtil

    fun getBaseActivity(): BaseActivity {
        return activity as BaseActivity
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = getBaseActivity()
        click = getBaseActivity().click
    }

    fun setContentView(inflater: LayoutInflater, resId: Int): View {
        mView = inflater.inflate(resId, null)
        mView.context?.let { mContext = it }
        mView.setOnClickListener {  }
        return mView
    }

    fun onBackPressed(): Boolean {
        return true
    }

    override fun pushFragment(fragment: BaseFragment) {
        getBaseActivity().pushFragment(fragment)
    }

    override fun pushUpFragment(fragment: BaseFragment) {
        getBaseActivity().pushUpFragment(fragment)
    }

    override fun pushMainFragment(fragment: BaseFragment) {
        getBaseActivity().pushMainFragment(fragment)
    }

    open fun apiStores(): ApiStores? {
        return getBaseActivity().apiStores
    }

    override fun setStatusBarNoAnimation(hex: Int) {
        statusBarHexColor = hex

        getBaseActivity().window?.statusBarColor = statusBarHexColor
    }

    override fun setStatusBarResID(id: Int) {
        statusBarResID = id
        getBaseActivity().setFragmentStatusBarColor(id)
    }

    override fun showToast(message: String?) {
        message?.let {
            if (it.isNotEmpty())
                Toast.makeText(getBaseActivity(), message, Toast.LENGTH_LONG).show()
        }
    }

    override fun showLoading() {
        getBaseActivity().loadingState.onNext(true)
    }

    override fun hideLoading() {
        getBaseActivity().loadingState.onNext(false)
    }

    override fun hideKeyboard() {
        getBaseActivity().hideKeyboard()
    }

    override fun onDestroy() {
        super.onDestroy()
        onUnsubscribe()
    }

    open fun onUnsubscribe() {
        compositeDisposable.dispose()
    }

    open fun addSubscription(observer: Disposable) {
        compositeDisposable.add(observer)
    }
}