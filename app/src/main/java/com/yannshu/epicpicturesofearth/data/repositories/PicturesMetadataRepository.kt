package com.yannshu.epicpicturesofearth.data.repositories

import android.arch.lifecycle.LiveData
import com.yannshu.epicpicturesofearth.data.local.PictureMetadataDao
import com.yannshu.epicpicturesofearth.data.model.PictureMetadata
import com.yannshu.epicpicturesofearth.data.network.PictureMetadataApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PicturesMetadataRepository(picturesMetadataApi: PictureMetadataApi, pictureMetadataDao: PictureMetadataDao) {

    val mPicturesMetadataApi: PictureMetadataApi = picturesMetadataApi

    val mPicturesMetadataDao: PictureMetadataDao = pictureMetadataDao

    fun getPicturesMetadata(quality: String, date: String): LiveData<List<PictureMetadata>> {
        refreshPicturesMetadata(quality, date)
        return mPicturesMetadataDao.loadByDate(date + "%")
    }

    fun getPictureMetadata(image: String): LiveData<PictureMetadata> {
        return mPicturesMetadataDao.loadByImage(image)
    }

    private fun refreshPicturesMetadata(quality: String, date: String) {
        mPicturesMetadataApi.getPicturesMetadata(quality, date).enqueue(object: Callback<List<PictureMetadata>> {
            override fun onFailure(call: Call<List<PictureMetadata>>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<List<PictureMetadata>>?, response: Response<List<PictureMetadata>>?) {
                var picturesMetadata = response?.body()

                if (picturesMetadata != null) {
                    for (pictureMetadata in picturesMetadata) {
                        pictureMetadata.quality = quality
                        mPicturesMetadataDao.save(pictureMetadata)
                    }
                }
            }
        })
    }
}