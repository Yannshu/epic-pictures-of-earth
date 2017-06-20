package com.yannshu.epicpicturesofearth.view.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.yannshu.epicpicturesofearth.data.model.PictureMetadata
import com.yannshu.epicpicturesofearth.data.repositories.PicturesMetadataRepository


class PicturesMetadataViewModel(repository: PicturesMetadataRepository): ViewModel() {

    var mRepository: PicturesMetadataRepository = repository

    var mDate: String? = null
    var mPicturesMetadata: LiveData<List<PictureMetadata>>? = null

    fun init(date: String) {
        mDate = date
        if (mDate != null) {
            mPicturesMetadata = mRepository.getPicturesMetadata("enhanced", mDate!!)
        }
    }
}