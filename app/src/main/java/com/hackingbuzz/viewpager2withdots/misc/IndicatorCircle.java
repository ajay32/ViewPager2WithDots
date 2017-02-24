package com.hackingbuzz.viewpager2withdots.misc;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;


public class IndicatorCircle implements ViewPager.OnPageChangeListener {  // we wants dots to work when we change page..so implements this interface
    private Context mContext;
    private LinearLayout ll_circle;
    private int circle_design;
    private int mSpacing;
    private int mSize;
    private ViewPager viewPager;
    private int mPageCount;
    private int mInitialPage = 0;

    private int defaultSizeInDp = 16;          // size of circle  // perfect size 12
    private int defaultSpacingInDp = 16;       // gap between circles             12


    // to create those small circles we required these things.. if you dont pass any of these it will give exception

    public IndicatorCircle( Context context, LinearLayout containerView, ViewPager viewPager, int drawableRes) {
        if (context == null) {
            throw new IllegalArgumentException("context cannot be null");
        } else if (containerView == null) {
            throw new IllegalArgumentException("containerView cannot be null");
        } else if (viewPager == null) {
            throw new IllegalArgumentException("ViewPager cannot be null");
        } else if (viewPager.getAdapter() == null) {
            throw new IllegalArgumentException("ViewPager does not have an adapter set on it.");
        }
        mContext = context;
        ll_circle = containerView;
        circle_design = drawableRes;
        this.viewPager = viewPager;

    }

    // our created methods//

    public void setPageCount(int pageCount) {
        mPageCount = pageCount;
    }

    public void setInitialPage(int page) {
        mInitialPage = page;
    }

    public void setDrawable(@DrawableRes int drawable) {
        circle_design = drawable;
    }

    public void setSpacingRes(@DimenRes int spacingRes) {
        mSpacing = spacingRes;
    }

    public void setSize(@DimenRes int dimenRes) {
        mSize = dimenRes;
    }

    public void show() {
        initIndicators();
        setIndicatorAsSelected(mInitialPage);
    }

    private void initIndicators() {
        if (ll_circle == null || mPageCount <= 0) {
            return;
        }

        viewPager.addOnPageChangeListener(this);
        Resources res = mContext.getResources();
        ll_circle.removeAllViews();
        for (int i = 0; i < mPageCount; i++) {
            View view = new View(mContext);
            int dimen = mSize != 0 ? res.getDimensionPixelSize(mSize) : ((int) res.getDisplayMetrics().density * defaultSizeInDp);
            int margin = mSpacing != 0 ? res.getDimensionPixelSize(mSpacing) : ((int) res.getDisplayMetrics().density * defaultSpacingInDp);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dimen, dimen);
            lp.setMargins(i == 0 ? 0 : margin, 0, 0, 0);
            view.setLayoutParams(lp);
            view.setBackgroundResource(circle_design);
            view.setSelected(i == 0);
            ll_circle.addView(view);
        }
    }

    private void setIndicatorAsSelected(int index) {
        if (ll_circle == null) {
            return;
        }
        for (int i = 0; i < ll_circle.getChildCount(); i++) {
            View view = ll_circle.getChildAt(i);
            view.setSelected(i == index);
        }
    }


    // methods of onPageChangeListerner


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {  // as we select the page calling IndicatorAsSelected method that will set dots accordingly
        int index = position % mPageCount;
        setIndicatorAsSelected(index);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void cleanup() {
        viewPager.clearOnPageChangeListeners();
    }
}
