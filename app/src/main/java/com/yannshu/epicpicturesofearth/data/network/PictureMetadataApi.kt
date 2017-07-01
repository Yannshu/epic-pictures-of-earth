package com.yannshu.epicpicturesofearth.data.network

import com.yannshu.epicpicturesofearth.data.model.PictureMetadata
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PictureMetadataApi {
    @GET("{quality}/date/{date}")
    fun getPicturesMetadata(@Path("quality") quality: String, @Path("date") date: String): Call<List<PictureMetadata>>
}