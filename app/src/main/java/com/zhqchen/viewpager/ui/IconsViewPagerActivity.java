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
public class IconsViewPagerActivity extends BaseIndicatorActivity {

    private int[] PAGER_ICONS = {
            R.mipmap.ic_work_white_24dp,
            R.mipmap.ic_grade_white_24dp,
            R.mipmap.ic_hourglass_empty_white_24dp,
            R.mipmap.ic_alarm_white_24dp
    };

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
        return PAGER_ICONS.length;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_view_pager;
    }

    @Override
    protected void initToolBar() {

    }

    private void initViewsValue() {
        mAdapter = new FragmentArrayPagerAdapter(getSupportFragmentManager(), null, PAGER_ICONS);
        for(int i = 0; i < PAGER_ICONS.length; i++) {
            IndicatorFragment fragment = new IndicatorFragment();
            mAdapter.add(fragment);
        }
        mViewPager.setAdapter(mAdapter);
        indicatorTopView.setViewPager(mViewPager, PAGER_ICONS.length);

        indicatorTopView.addOnPageChangeListener(new IndicatorPageChangedListener() {

        });
    }
}
