package com.yannshu.epicpicturesofearth.data.repositories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.yannshu.epicpicturesofearth.data.model.PictureMetadata
import com.yannshu.epicpicturesofearth.data.network.PictureMetadataApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PicturesMetadataRepository(picturesMetadataApi: PictureMetadataApi) {

    val mPicturesMetadataApi: PictureMetadataApi = picturesMetadataApi

    fun getPicturesMetadata(quality: String, date: String): LiveData<List<PictureMetadata>> {
        val data: MutableLiveData<List<PictureMetadata>> = MutableLiveData<List<PictureMetadata>>()

        mPicturesMetadataApi.getPicturesMetadata(quality, date).enqueue(object: Callback<List<PictureMetadata>> {
            override fun onFailure(call: Call<List<PictureMetadata>>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<List<PictureMetadata>>?, response: Response<List<PictureMetadata>>?) {
                data.value = response?.body()
            }
        })
        return data
    }
}