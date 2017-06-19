package com.yannshu.epicpicturesofearth.ui.base


open abstract class BasePresenter<T : MvpView> {

    protected var mMvp : T? = null

    fun attachView(mvp : T) {
        mMvp = mvp
    }

    fun detachView() {
        mMvp = null
    }

    fun getMvp() : T? {
        return mMvp
    }
}