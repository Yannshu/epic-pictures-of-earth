package com.yannshu.epicpicturesofearth.view.activities

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.yannshu.epicpicturesofearth.data.repositories.PicturesMetadataRepository
import com.yannshu.epicpicturesofearth.di.activity.ActivityComponent
import com.yannshu.epicpicturesofearth.di.activity.ActivityComponentBuilder
import com.yannshu.epicpicturesofearth.di.activity.ActivityModule
import com.yannshu.epicpicturesofearth.di.activity.ActivityScope
import com.yannshu.epicpicturesofearth.utils.PictureUrlBuilder
import com.yannshu.epicpicturesofearth.view.adapters.PicturesAdapter
import com.yannshu.epicpicturesofearth.view.model.PicturesMetadataViewModel
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = arrayOf(HomeActivityComponent.HomeActivityModule::class))
interface HomeActivityComponent : ActivityComponent<HomeActivity> {

    @Subcomponent.Builder
    interface Builder : ActivityComponentBuilder<HomeActivityModule, HomeActivityComponent>

    @Module
    class HomeActivityModule(activity: HomeActivity, quality: String) : ActivityModule<HomeActivity>(activity) {

        val mContext: Context = activity

        val mQuality: String = quality

        @ActivityScope
        @Provides
        fun providePicturesMetadataViewModel(repository: PicturesMetadataRepository): PicturesMetadataViewModel {
            return PicturesMetadataViewModel(repository)
        }

        @Provides
        fun provideLayoutManager(): RecyclerView.LayoutManager {
            return LinearLayoutManager(mContext)
        }

        @Provides
        fun providePicturesAdapter(picturesUrlBuilder: PictureUrlBuilder): PicturesAdapter {
            return PicturesAdapter(mContext, mQuality, picturesUrlBuilder)
        }
    }
}
