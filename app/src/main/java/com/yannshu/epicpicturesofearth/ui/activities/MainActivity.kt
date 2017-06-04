package com.yannshu.epicpicturesofearth.ui.activities

import android.os.Bundle
import com.yannshu.epicpicturesofearth.R
import com.yannshu.epicpicturesofearth.ui.fragments.MainFragment

class MainActivity : BaseActivity<MainFragment>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun initFragment() {
        mFragment = MainFragment()
        mFragmentContainer = R.id.fragment_container_layout
    }
}
