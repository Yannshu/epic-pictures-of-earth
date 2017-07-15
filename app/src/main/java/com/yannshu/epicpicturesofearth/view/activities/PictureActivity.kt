package com.yannshu.epicpicturesofearth.view.activities

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView
import com.yannshu.epicpicturesofearth.R
import com.yannshu.epicpicturesofearth.data.model.PictureMetadata
import com.yannshu.epicpicturesofearth.di.activity.HasActivitySubComponentBuilders
import com.yannshu.epicpicturesofearth.utils.PictureUrlBuilder
import com.yannshu.epicpicturesofearth.view.base.BaseActivity
import com.yannshu.epicpicturesofearth.view.model.PictureMetadataViewModel
import javax.inject.Inject


class PictureActivity : BaseActivity() {

    companion object {

        val KEY_PICTURES_METADATA_IMAGE: String = "KEY_PICTURES_METADATA_IMAGE"

        fun createStartingIntent(context: Context, pictureMetadata: PictureMetadata): Intent {
            val intent = Intent(context, PictureActivity::class.java)
            intent.putExtra(KEY_PICTURES_METADATA_IMAGE, pictureMetadata.image)
            return intent
        }
    }

    @BindView(R.id.photo_view)
    lateinit var mPhotoView: PhotoView

    @Inject
    lateinit var mPictureUrlBuilder: PictureUrlBuilder

    @Inject
    lateinit var mViewModel: PictureMetadataViewModel

    var mPictureMetadataImage: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)
        ButterKnife.bind(this)
        initActionBar(true)

        val pictureMetadataImage = mPictureMetadataImage
        if (pictureMetadataImage != null) {
            mViewModel.init(pictureMetadataImage)
            mViewModel.mPictureMetadata?.observe(this, object : Observer<PictureMetadata> {
                override fun onChanged(pictureMetadata: PictureMetadata?) {
                    Log.d("data", "picture: " + pictureMetadata?.image)
                    if (pictureMetadata != null) {
                        Glide.with(applicationContext)
                                .load(mPictureUrlBuilder.buildUrl(pictureMetadata))
                                .into(mPhotoView)
                    }
                }
            })
        }
    }

    override fun retrieveIntentBundle(extras: Bundle?) {
        if (extras != null && extras.containsKey(KEY_PICTURES_METADATA_IMAGE)) {
            mPictureMetadataImage = extras.getString(KEY_PICTURES_METADATA_IMAGE)
        }
    }

    override fun injectMembers(hasActivitySubComponentBuilders: HasActivitySubComponentBuilders) {
        (hasActivitySubComponentBuilders
                .getActivityComponentBuilder(PictureActivity::class.java) as PictureActivityComponent.Builder)
                .activityModule(PictureActivityComponent.PictureActivityModule(this))
                .build()
                .injectMembers(this)
    }
}