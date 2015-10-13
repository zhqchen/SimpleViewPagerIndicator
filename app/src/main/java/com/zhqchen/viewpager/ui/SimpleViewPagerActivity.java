package com.zhqchen.viewpager.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.zhqchen.viewpager.R;
import com.zhqchen.viewpager.adapter.FragmentArrayPagerAdapter;

/**
 * Created by zqchen
 */
public class SimpleViewPagerActivity extends BaseIndicatorActivity {

    private int[] PAGER_TITLE = {R.string.indicator_item_1, R.string.indicator_item_2, R.string.indicator_item_3, R.string.indicator_item_4};

    @InjectView(R.id.vp_main)
    ViewPager mViewPager;

    FragmentArrayPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        initViewsValue();
    }

    @Override
    protected int getTabViewCount() {
        return PAGER_TITLE.length;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_view_pager;
    }

    @Override
    protected void initToolBar() {

    }

    private void initViewsValue() {
        mAdapter = new FragmentArrayPagerAdapter(getSupportFragmentManager());
        for(int i = 0; i < PAGER_TITLE.length; i++) {
            IndicatorFragment fragment = new IndicatorFragment();
            mAdapter.add(fragment);
        }
        mAdapter.setPageTitle(PAGER_TITLE);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(PAGER_TITLE.length - 1);
        indicatorTopView.setViewPager(mViewPager);

        indicatorTopView.addOnPageChangeListener(new IndicatorPageChangedListener() {

        });
    }
}
