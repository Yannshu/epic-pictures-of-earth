package com.yannshu.epicpicturesofearth.view.activities

import android.content.Context
import com.yannshu.epicpicturesofearth.data.repositories.PicturesMetadataRepository
import com.yannshu.epicpicturesofearth.di.activity.ActivityComponent
import com.yannshu.epicpicturesofearth.di.activity.ActivityComponentBuilder
import com.yannshu.epicpicturesofearth.di.activity.ActivityModule
import com.yannshu.epicpicturesofearth.di.activity.ActivityScope
import com.yannshu.epicpicturesofearth.view.model.PictureMetadataViewModel
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = arrayOf(PictureActivityComponent.PictureActivityModule::class))
interface PictureActivityComponent : ActivityComponent<PictureActivity> {

    @Subcomponent.Builder
    interface Builder : ActivityComponentBuilder<PictureActivityModule, PictureActivityComponent>

    @Module
    class PictureActivityModule(activity: PictureActivity) : ActivityModule<PictureActivity>(activity) {

        val mContext: Context = activity

        @ActivityScope
        @Provides
        fun providePicturesMetadataViewModel(repository: PicturesMetadataRepository): PictureMetadataViewModel {
            return PictureMetadataViewModel(repository)
        }
    }
}
