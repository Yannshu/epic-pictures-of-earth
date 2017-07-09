package com.yannshu.epicpicturesofearth.view.activities

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.yannshu.epicpicturesofearth.R
import com.yannshu.epicpicturesofearth.data.repositories.PicturesMetadataRepository
import com.yannshu.epicpicturesofearth.di.activity.ActivityComponent
import com.yannshu.epicpicturesofearth.di.activity.ActivityComponentBuilder
import com.yannshu.epicpicturesofearth.di.activity.ActivityModule
import com.yannshu.epicpicturesofearth.di.activity.ActivityScope
import com.yannshu.epicpicturesofearth.utils.PictureUrlBuilder
import com.yannshu.epicpicturesofearth.view.adapters.PicturesAdapter
import com.yannshu.epicpicturesofearth.view.custom.SpacingItemDecoration
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
    class HomeActivityModule(activity: HomeActivity) : ActivityModule<HomeActivity>(activity) {

        val mContext: Context = activity

        @ActivityScope
        @Provides
        fun providePicturesMetadataViewModel(repository: PicturesMetadataRepository): PicturesMetadataViewModel {
            return PicturesMetadataViewModel(repository)
        }

        @Provides
        fun provideLayoutManager(itemDecoration: RecyclerView.ItemDecoration): RecyclerView.LayoutManager {
            return LinearLayoutManager(mContext)
        }

        @Provides
        fun provideItemDecoration(): RecyclerView.ItemDecoration {
            val itemDecoration: SpacingItemDecoration = SpacingItemDecoration(mContext.resources)
            itemDecoration.setVerticalSpacing(R.dimen.cardview_margin)
            return itemDecoration
        }

        @Provides
        fun providePicturesAdapter(picturesUrlBuilder: PictureUrlBuilder): PicturesAdapter {
            return PicturesAdapter(mContext, picturesUrlBuilder)
        }
    }
}
