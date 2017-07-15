package com.yannshu.epicpicturesofearth.view.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.yannshu.epicpicturesofearth.data.model.PictureMetadata
import com.yannshu.epicpicturesofearth.data.repositories.PicturesMetadataRepository


class PictureMetadataViewModel(repository: PicturesMetadataRepository) : ViewModel() {

    var mRepository: PicturesMetadataRepository = repository

    var mPictureMetadata: LiveData<PictureMetadata>? = null

    fun init(image: String) {
        mPictureMetadata = mRepository.getPictureMetadata(image)
    }
}