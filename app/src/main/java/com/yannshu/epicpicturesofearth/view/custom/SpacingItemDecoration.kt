package com.yannshu.epicpicturesofearth.view.custom


import android.content.res.Resources
import android.graphics.Rect
import android.support.annotation.DimenRes
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by laurence on 25/01/2017.
 */

class SpacingItemDecoration(resources: Resources) : RecyclerView.ItemDecoration() {

    companion object {
        private val TWO = 2
    }

    private var mSpaceLeft: Int = 0

    private var mSpaceRight: Int = 0

    private var mSpaceTop: Int = 0

    private var mSpaceBottom: Int = 0

    private val mResources: Resources = resources

    fun setSpaceLeft(spaceLeft: Int) {
        mSpaceLeft = getDimensionPixels(spaceLeft)
    }

    fun setSpaceRight(spaceRight: Int) {
        mSpaceRight = getDimensionPixels(spaceRight)
    }

    fun setSpaceTop(spaceTop: Int) {
        mSpaceTop = getDimensionPixels(spaceTop)
    }

    fun setSpaceBottom(spaceBottom: Int) {
        mSpaceBottom = getDimensionPixels(spaceBottom)
    }

    fun setVerticalSpacing(@DimenRes verticalSpace: Int) {
        val mVerticalSpace = getDimensionPixels(verticalSpace)
        mSpaceTop = mVerticalSpace / TWO
        mSpaceBottom = mVerticalSpace / TWO
    }

    fun setHorizontalSpacing(@DimenRes horizontalSpacing: Int) {
        val mHorizontalSpace = getDimensionPixels(horizontalSpacing)
        mSpaceLeft = mHorizontalSpace / TWO
        mSpaceRight = mHorizontalSpace / TWO
    }

    override fun getItemOffsets(outRect: Rect, view: View,
                                parent: RecyclerView, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(mSpaceLeft, mSpaceTop, mSpaceRight, mSpaceBottom)
    }

    private fun getDimensionPixels(@DimenRes dimenRes: Int): Int {
        return mResources.getDimensionPixelSize(dimenRes)
    }
}