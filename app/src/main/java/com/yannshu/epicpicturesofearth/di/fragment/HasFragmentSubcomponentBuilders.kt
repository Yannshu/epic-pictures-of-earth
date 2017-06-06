package com.yannshu.epicpicturesofearth.di.fragment

import android.support.v4.app.Fragment

interface HasFragmentSubcomponentBuilders {
    fun getFragmentComponentBuilder(fragmentClass: Class<out Fragment>): FragmentComponentBuilder<*, *>
}