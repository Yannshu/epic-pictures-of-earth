package com.yannshu.epicpicturesofearth.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yannshu.epicpicturesofearth.ui.fragments.BaseFragment
import com.yannshu.epicpicturesofearth.utils.Constants

abstract class BaseActivity<T : BaseFragment> : AppCompatActivity() {

    protected var mFragment: T? = null

    protected var mFragmentContainer = Constants.DEFAULT_INT_VALUE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFragment()
        launchFragment()
    }

    protected open fun initFragment() {
    }

    private fun launchFragment() {
        if (mFragment != null && mFragmentContainer != Constants.DEFAULT_INT_VALUE) {
            var currentFragment = supportFragmentManager.findFragmentById(mFragmentContainer)

            if (currentFragment == null) {
                supportFragmentManager.beginTransaction().add(mFragmentContainer, mFragment).commit()
            } else {
                mFragment = currentFragment as T
            }
        }
    }
}