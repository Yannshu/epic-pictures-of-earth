package com.yannshu.epicpicturesofearth.view.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.yannshu.epicpicturesofearth.data.model.PictureMetadata
import com.yannshu.epicpicturesofearth.data.repositories.PicturesMetadataRepository


class PicturesMetadataListViewModel(repository: PicturesMetadataRepository): ViewModel() {

    var mRepository: PicturesMetadataRepository = repository

    var mDate: String? = null
    var mPicturesMetadata: LiveData<List<PictureMetadata>>? = null

    fun getPictures(quality: String, date: String) {
        mDate = date
        if (mDate != null) {
            mPicturesMetadata = mRepository.getPicturesMetadata(quality, date)
        }
    }
}