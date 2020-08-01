package com.hdh.backpacker_task.ui.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hdh.backpacker_task.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

open class BaseActivity : AppCompatActivity() {

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }
    var fragmentList: ArrayList<BaseFragment> = ArrayList()
    private var doubleBackToExitPressedOnce = false
    val loadingState = PublishSubject.create<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    override fun onBackPressed() {
        if (fragmentList.size > 1) {
            if (!fragmentList[fragmentList.size - 1].onBackPressed()) {
                return
            }

            fragmentList[fragmentList.size - 1].popFragment()
            return
        }

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true

        Toast.makeText(this, getString(R.string.double_back_to_exit_pressed_once_text), Toast.LENGTH_SHORT).show()
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 1500)
    }

    fun getTopFragment(): BaseFragment? {
        return if (fragmentList.size <= 0) null else fragmentList[fragmentList.size - 1]
    }

    fun removeBackAllFragment() {
        Handler().postDelayed({
            while (fragmentList.size > 1) {
                popBackStack(false, fragmentList[0], false)
                fragmentList.removeAt(0)
            }
        }, 600)
    }

    open fun removeAllFragment() {
        val currentFragment = fragmentList.size - 1
        while (fragmentList.size > 0) {
            if (currentFragment == fragmentList.size - 1) {
                popBackStack(false, fragmentList[fragmentList.size - 1], true)
            } else {
                popBackStack(false, fragmentList[fragmentList.size - 1], false)
            }
            fragmentList.removeAt(fragmentList.size - 1)
        }
    }

    fun onReturn() {

    }

    fun popBackStack(isReturn: Boolean, fragment: BaseFragment, isAnim: Boolean) {
        if (isReturn) {
            if (fragmentList.size > 1) {
                fragmentList[fragmentList.size - 2].onReturn()
            } else {
                onReturn()
            }
        }
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        if (isAnim) {
            transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
        }
        transaction.remove(fragment)
        transaction.commitAllowingStateLoss()
        if (isAnim) {
            if (fragmentList.size > 1) {
                val xmlAnimation = AnimationUtils.loadAnimation(baseContext, R.anim.fade_in)
                fragmentList[fragmentList.size - 2].getMotherView()?.startAnimation(xmlAnimation)
            }
        }
    }

    fun popBackStack(fragment: BaseFragment, isAnim: Boolean) {
        popBackStack(true, fragment, isAnim)
    }

    fun pushFragment(fragment: BaseFragment) {
        if (null != findViewById(R.id.fragment_container)) {
            findViewById<View>(R.id.fragment_container).bringToFront()
        }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
        transaction.add(R.id.fragment_container, fragment, fragment.javaClass.name)
        transaction.commitAllowingStateLoss()
        if (getTopFragment() != null) {
            val xmlAnimation = AnimationUtils.loadAnimation(baseContext, R.anim.fade_out)
            getTopFragment()?.getMotherView()?.startAnimation(xmlAnimation)
        }
    }

    fun pushMainFragment(fragment: BaseFragment) {
        pushFragment(fragment)
        removeBackAllFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        onUnsubscribe()
    }

    @SuppressLint("CheckResult")
    open fun addSubscription(observable: Observable<*>, observer: DisposableObserver<*>) {
        compositeDisposable.add(observer)

        observable
            .subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(observer as DisposableObserver<Any>)
    }

    open fun addSubscription(observer: Disposable) {
        compositeDisposable.add(observer)
    }

    private fun onUnsubscribe() {
        compositeDisposable.dispose()
    }

}
