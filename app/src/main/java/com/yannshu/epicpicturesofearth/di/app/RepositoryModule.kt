package com.yannshu.epicpicturesofearth.di.app

import android.arch.persistence.room.Room
import android.content.Context
import com.yannshu.epicpicturesofearth.data.local.PictureMetadataDao
import com.yannshu.epicpicturesofearth.data.local.PictureMetadataDatabase
import com.yannshu.epicpicturesofearth.data.network.PictureMetadataApi
import com.yannshu.epicpicturesofearth.data.repositories.PicturesMetadataRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    fun providePicturesMetadataRepository(pictureMetadataApi: PictureMetadataApi, pictureMetadataDao: PictureMetadataDao): PicturesMetadataRepository {
        return PicturesMetadataRepository(pictureMetadataApi, pictureMetadataDao)
    }

    @Provides
    @Singleton
    fun providePicturesMetadataDatabase(context: Context): PictureMetadataDatabase {
        return Room.databaseBuilder(context, PictureMetadataDatabase::class.java, "PictureMetadata").allowMainThreadQueries().build()
    }

    @Provides
    fun providePicturesMetadataDao(pictureMetadataDatabase: PictureMetadataDatabase): PictureMetadataDao {
        return pictureMetadataDatabase.pictureMetadataDao()
    }
}
