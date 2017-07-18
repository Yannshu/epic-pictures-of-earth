package com.yannshu.epicpicturesofearth.view.activities

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.yannshu.epicpicturesofearth.R
import com.yannshu.epicpicturesofearth.data.model.PictureMetadata
import com.yannshu.epicpicturesofearth.di.activity.HasActivitySubComponentBuilders
import com.yannshu.epicpicturesofearth.view.adapters.PicturesAdapter
import com.yannshu.epicpicturesofearth.view.base.BaseActivity
import com.yannshu.epicpicturesofearth.view.model.PicturesMetadataListViewModel
import javax.inject.Inject


class HomeActivity : BaseActivity() {

    @Inject
    lateinit var mViewModel: PicturesMetadataListViewModel

    @Inject
    lateinit var mAdapter: PicturesAdapter

    @Inject
    lateinit var mLayoutManager: RecyclerView.LayoutManager

    @Inject
    lateinit var mItemDecoration: RecyclerView.ItemDecoration

    @BindView(R.id.pictures_recycler_view)
    lateinit var mRecyclerView: RecyclerView

    @BindView(R.id.title_text_view)
    lateinit var mTitleTextView: TextView

    @BindView(R.id.subtitle_text_view)
    lateinit var mSubtitleTextView: TextView

    var mQuality: String = "natural"

    var mDate: String = "2017-06-20"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        ButterKnife.bind(this)
        initLayout()

        mViewModel.init(mQuality, mDate)
        mViewModel.mPicturesMetadata?.observe(this, object : Observer<List<PictureMetadata>> {
            override fun onChanged(picturesMetadata: List<PictureMetadata>?) {
                Log.d("data", "pictures: " + picturesMetadata?.size)
                if (picturesMetadata != null) {
                    mAdapter.mData = picturesMetadata
                    mAdapter.notifyDataSetChanged()
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

    private fun initLayout() {
        initRecyclerView()
        initActionBar(false)
        setTitle(getString(R.string.app_name))
        setSubtitle(mDate)
    }

    private fun initRecyclerView() {
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.addItemDecoration(mItemDecoration)
        mRecyclerView.layoutManager = mLayoutManager
        mRecyclerView.adapter = mAdapter
        mAdapter.mListener = object : PicturesAdapter.Listener {
            override fun onPictureClick(pictureMetadata: PictureMetadata) {
                val intent = PictureActivity.createStartingIntent(baseContext, pictureMetadata)
                baseContext.startActivity(intent)
            }
        }
    }

    private fun setTitle(title: String) {
        mTitleTextView.text = title
    }

    private fun setSubtitle(subtitle: String) {
        mSubtitleTextView.text = subtitle
    }
}
