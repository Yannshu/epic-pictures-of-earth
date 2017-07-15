package com.yannshu.epicpicturesofearth.data.local

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.yannshu.epicpicturesofearth.data.model.PictureMetadata

@Dao
interface PictureMetadataDao {

    @Insert(onConflict = REPLACE)
    fun save(pictureMetadata: PictureMetadata)

    @Query("SELECT * FROM PictureMetadata WHERE date LIKE :date ORDER BY date ASC")
    fun loadByDate(date: String): LiveData<List<PictureMetadata>>

    @Query("SELECT * FROM PictureMetadata WHERE image = :image")
    fun loadByImage(image: String): LiveData<PictureMetadata>
}