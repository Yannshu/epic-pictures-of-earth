package com.yannshu.epicpicturesofearth.view.activities

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import butterknife.BindView
import butterknife.ButterKnife
import com.yannshu.epicpicturesofearth.R
import com.yannshu.epicpicturesofearth.data.model.PictureMetadata
import com.yannshu.epicpicturesofearth.di.activity.HasActivitySubComponentBuilders
import com.yannshu.epicpicturesofearth.view.adapters.PicturesAdapter
import com.yannshu.epicpicturesofearth.view.base.BaseActivity
import com.yannshu.epicpicturesofearth.view.model.PicturesMetadataViewModel
import javax.inject.Inject


class HomeActivity : BaseActivity() {

    @Inject
    lateinit var mViewModel: PicturesMetadataViewModel

    @Inject
    lateinit var mAdapter: PicturesAdapter

    @Inject
    lateinit var mLayoutManager: RecyclerView.LayoutManager

    @Inject
    lateinit var mItemDecoration: RecyclerView.ItemDecoration

    @BindView(R.id.pictures_recycler_view)
    lateinit var mRecyclerView: RecyclerView

    var mQuality: String = "natural"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        ButterKnife.bind(this)
        initRecyclerView()

        mViewModel.init(mQuality, "2017-06-20")
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

    private fun initRecyclerView() {
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.addItemDecoration(mItemDecoration)
        mRecyclerView.layoutManager = mLayoutManager
        mRecyclerView.adapter = mAdapter
    }
}
