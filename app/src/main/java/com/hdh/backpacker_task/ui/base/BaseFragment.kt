package com.hdh.backpacker_task.ui.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hdh.backpacker_task.data.ApiStores
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseFragment : Fragment(), BaseView {

    override lateinit var mContext: Context
    override lateinit var mActivity: BaseActivity

    private lateinit var mView: View
    private val compositeDisposable = CompositeDisposable()
    private var statusBarHexColor = 0

    fun getBaseActivity(): BaseActivity {
        return activity as BaseActivity
    }

    fun onBackPressed(): Boolean {
        return true
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = getBaseActivity()

        getBaseActivity().fragmentList.add(this)
    }

    fun setContentView(inflater: LayoutInflater, resId: Int): View {
        val decor = activity?.window?.decorView
        mView = inflater.inflate(resId, null)
        mView.context?.let { mContext = it }
        mView.setOnClickListener { }

        decor?.systemUiVisibility = 0
        return mView
    }

    override fun pushFragment(fragment: BaseFragment) {
        getBaseActivity().pushFragment(fragment)
    }

    override fun pushMainFragment(fragment: BaseFragment) {
        getBaseActivity().pushMainFragment(fragment)
    }

    override fun popFragment() {
        getBaseActivity().popBackStack(this, true)
    }

    /**
     * 상위 프레그먼트에서 돠돌아왔을때 호출
     */
    override fun onReturn() {

    }

    open fun getMotherView(): View? {
        return mView
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

    override fun onDestroy() {
        super.onDestroy()

        getBaseActivity().fragmentList.remove(this)
        getBaseActivity().window?.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
        onUnsubscribe()

    }

    open fun onUnsubscribe() {
        compositeDisposable.dispose()
    }

    open fun addSubscription(observer: Disposable) {
        compositeDisposable.add(observer)
    }
}