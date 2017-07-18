package com.yannshu.epicpicturesofearth.view.activities

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.github.sundeepk.compactcalendarview.CompactCalendarView
import com.yannshu.epicpicturesofearth.R
import com.yannshu.epicpicturesofearth.data.model.PictureMetadata
import com.yannshu.epicpicturesofearth.di.activity.HasActivitySubComponentBuilders
import com.yannshu.epicpicturesofearth.view.adapters.PicturesAdapter
import com.yannshu.epicpicturesofearth.view.base.BaseActivity
import com.yannshu.epicpicturesofearth.view.model.PicturesMetadataListViewModel
import java.text.SimpleDateFormat
import java.util.*
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

    @Inject
    lateinit var mDateFormat: SimpleDateFormat

    @BindView(R.id.pictures_recycler_view)
    lateinit var mRecyclerView: RecyclerView

    @BindView(R.id.app_bar_layout)
    lateinit var mAppBarLayout: AppBarLayout

    @BindView(R.id.title_text_view)
    lateinit var mTitleTextView: TextView

    @BindView(R.id.subtitle_text_view)
    lateinit var mSubtitleTextView: TextView

    @BindView(R.id.calendar_view)
    lateinit var mCalendarView: CompactCalendarView

    @BindView(R.id.date_picker_arrow)
    lateinit var mArrowImageView: ImageView

    var mQuality: String = "natural"

    var mIsExpanded: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        ButterKnife.bind(this)
        initLayout()
        setCurrentDate(Date())

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
        initCalendar();
        setTitle(getString(R.string.app_name))
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

    private fun initCalendar() {
        mCalendarView.setLocale(TimeZone.getDefault(), Locale.getDefault())
        mCalendarView.setShouldDrawDaysHeader(true)
        mCalendarView.setListener(object : CompactCalendarView.CompactCalendarViewListener {
            override fun onDayClick(dateClicked: Date) {
                setCurrentDate(dateClicked)
            }

            override fun onMonthScroll(firstDayOfNewMonth: Date) {
            }
        })
    }

    private fun setTitle(title: String) {
        mTitleTextView.text = title
    }

    private fun setSubtitle(subtitle: String) {
        mSubtitleTextView.text = subtitle
    }

    private fun setCurrentDate(date: Date) {
        val formattedDate = mDateFormat.format(date)
        if (formattedDate != null) {
            setSubtitle(formattedDate)
            mCalendarView.setCurrentDate(date)
            mViewModel.init(mQuality, formattedDate)
        }
    }

    @OnClick(R.id.date_picker_layout)
    fun onDatePickerClick(view: View) {
        if (mIsExpanded) {
            ViewCompat.animate(mArrowImageView).rotation(0.0f).start();
        } else {
            ViewCompat.animate(mArrowImageView).rotation(180.0f).start();
        }
        mIsExpanded = !mIsExpanded
        mAppBarLayout.setExpanded(mIsExpanded, true)
    }
}
