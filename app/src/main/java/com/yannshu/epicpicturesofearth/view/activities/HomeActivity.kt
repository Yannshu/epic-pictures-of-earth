package com.yannshu.epicpicturesofearth.view.activities

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.util.Log
import butterknife.BindView
import butterknife.ButterKnife
import com.daimajia.slider.library.SliderLayout
import com.daimajia.slider.library.SliderTypes.TextSliderView
import com.yannshu.epicpicturesofearth.R
import com.yannshu.epicpicturesofearth.data.model.PictureMetadata
import com.yannshu.epicpicturesofearth.di.activity.HasActivitySubComponentBuilders
import com.yannshu.epicpicturesofearth.utils.PictureUrlBuilder
import com.yannshu.epicpicturesofearth.view.base.BaseActivity
import com.yannshu.epicpicturesofearth.view.model.PicturesMetadataViewModel
import javax.inject.Inject


class HomeActivity : BaseActivity() {

    @Inject
    lateinit var mViewModel: PicturesMetadataViewModel

    @Inject
    lateinit var mPicturesUrlBuilder: PictureUrlBuilder

    @BindView(R.id.pictures_slider_layout)
    lateinit var mPicturesSliderLayout: SliderLayout

    var mQuality: String = "natural"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        ButterKnife.bind(this)

        mViewModel.init(mQuality, "2017-06-20")
        mViewModel.mPicturesMetadata?.observe(this, object : Observer<List<PictureMetadata>> {
            override fun onChanged(picturesMetadata: List<PictureMetadata>?) {
                if (picturesMetadata != null) {
                    mPicturesSliderLayout.removeAllSliders()
                    for (pictureMetadata: PictureMetadata in picturesMetadata) {
                        addToSliderLayout(pictureMetadata)
                    }
                }
                Log.d("HomeActivity", "Picture: " + picturesMetadata?.size)
            }
        })
    }

    override fun injectMembers(hasActivitySubComponentBuilders: HasActivitySubComponentBuilders) {
        (hasActivitySubComponentBuilders
                .getActivityComponentBuilder(HomeActivity::class.java) as HomeActivityComponent.Builder)
                .activityModule(HomeActivityComponent.HomeActivityModule(this))
                .build()
                .injectMembers(this)
    }

    private fun addToSliderLayout(pictureMetadata: PictureMetadata) {
        var textSliderView: TextSliderView = TextSliderView(this)
        textSliderView.description(pictureMetadata.date)
        textSliderView.image(mPicturesUrlBuilder.buildUrl(mQuality, pictureMetadata))
        mPicturesSliderLayout.addSlider(textSliderView)
    }
}
