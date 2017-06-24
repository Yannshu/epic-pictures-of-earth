package com.yannshu.epicpicturesofearth.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.yannshu.epicpicturesofearth.data.model.PictureMetadata


@Database(entities = arrayOf(PictureMetadata::class), version = 1)
abstract class PictureMetadataDatabase: RoomDatabase() {
    abstract fun pictureMetadataDao(): PictureMetadataDao
}