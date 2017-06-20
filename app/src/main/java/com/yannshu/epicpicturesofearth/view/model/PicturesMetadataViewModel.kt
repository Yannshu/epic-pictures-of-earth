package com.yannshu.epicpicturesofearth.view.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.yannshu.epicpicturesofearth.data.model.PictureMetadata


class PicturesMetadataViewModel: ViewModel() {

    var mDate: String? = null
    var mPicturesMetadata: LiveData<List<PictureMetadata>>? = null

    fun init(date: String) {
        mDate = date
    }

}