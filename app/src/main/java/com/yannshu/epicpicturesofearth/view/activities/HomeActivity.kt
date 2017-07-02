package com.yannshu.epicpicturesofearth.view.activities

import android.arch.lifecycle.Observer
import android.os.Bundle
import butterknife.BindView
import butterknife.ButterKnife
import com.daimajia.slider.library.SliderLayout
import com.daimajia.slider.library.SliderTypes.BaseSliderView
import com.daimajia.slider.library.SliderTypes.TextSliderView
import com.yannshu.epicpicturesofearth.R
import com.yannshu.epicpicturesofearth.data.model.PictureMetadata
import com.yannshu.epicpicturesofearth.di.activity.HasActivitySubComponentBuilders
import com.yannshu.epicpicturesofearth.utils.PictureUrlBuilder
import com.yannshu.epicpicturesofearth.view.base.BaseActivity
import com.yannshu.epicpicturesofearth.view.model.PicturesMetadataViewModel
import javax.inject.Inject


class HomeActivity : BaseActivity() {

    object Constants {
        val PICTURE_DISPLAY_DURATION_MS: Long = 10000
    }

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
        initPicturesSlider()

        mViewModel.init(mQuality, "2017-06-20")
        mViewModel.mPicturesMetadata?.observe(this, object : Observer<List<PictureMetadata>> {
            override fun onChanged(picturesMetadata: List<PictureMetadata>?) {
                if (picturesMetadata != null) {
                    mPicturesSliderLayout.removeAllSliders()
                    for (pictureMetadata: PictureMetadata in picturesMetadata) {
                        addToSliderLayout(pictureMetadata)
                    }
                }
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

    override fun onStop() {
        super.onStop()
        mPicturesSliderLayout.stopAutoCycle()
    }

    private fun initPicturesSlider() {
        mPicturesSliderLayout.setDuration(Constants.PICTURE_DISPLAY_DURATION_MS)
    }

    private fun addToSliderLayout(pictureMetadata: PictureMetadata) {
        var textSliderView: TextSliderView = TextSliderView(this)
        textSliderView.description(pictureMetadata.date)
        textSliderView.image(mPicturesUrlBuilder.buildUrl(mQuality, pictureMetadata))
        textSliderView.setScaleType(BaseSliderView.ScaleType.CenterInside)
        mPicturesSliderLayout.addSlider(textSliderView)
    }
}
