package com.yannshu.epicpicturesofearth.di.app

import com.yannshu.epicpicturesofearth.data.network.PictureMetadataApi
import com.yannshu.epicpicturesofearth.data.repositories.PicturesMetadataRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun providePicturesMetadataRepository(pictureMetadataApi: PictureMetadataApi): PicturesMetadataRepository {
        return PicturesMetadataRepository(pictureMetadataApi)
    }
}
