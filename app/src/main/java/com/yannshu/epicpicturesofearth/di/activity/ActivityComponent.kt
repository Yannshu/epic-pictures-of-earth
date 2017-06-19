package com.yannshu.epicpicturesofearth.di.activity

import android.app.Activity

import dagger.MembersInjector

interface ActivityComponent<A : Activity> : MembersInjector<A>
