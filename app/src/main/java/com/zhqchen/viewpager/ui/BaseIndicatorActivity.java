package com.zhqchen.viewpager.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.zhqchen.viewpager.R;
import com.zhqchen.viewpager.util.DeviceUtil;
import com.zhqchen.viewpager.view.IndicatorTopView;

/**
 * Created by chen on 15/7/23.
 */
public abstract class BaseIndicatorActivity extends AppCompatActivity {

    private boolean isIndicatorLayoutVisible = true; //指示栏View的显示状态

    protected Toolbar tbHeader;

    protected IndicatorTopView indicatorTopView;//整个顶部栏

    protected View llLineIndicator;//滑动的线

    private int indicatorHeight = 0;//顶部栏的高度

    private int indicatorMoveWidth = 0;//滑线的宽度

    /**
     * 子类使用这个Listener，来控制实现顶部的滑动条
     */
    protected class IndicatorPageChangedListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            llLineIndicator.scrollTo(-position*indicatorMoveWidth - Math.round(positionOffset * indicatorMoveWidth), 0);
        }

        @Override
        public void onPageSelected(int position) {
            indicatorTopView.changeOperationTextViews(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        tbHeader = (Toolbar) findViewById(R.id.tb_header);
        if(tbHeader != null) {
            setSupportActionBar(tbHeader);
            initToolBar();
        }

        indicatorMoveWidth = DeviceUtil.getDeviceScreenWidth(this) / getTabViewCount();
        llLineIndicator = findViewById(R.id.ll_line_indicator);
        ((TextView)findViewById(R.id.tv_scroll_line)).setWidth(indicatorMoveWidth);

        indicatorTopView = (IndicatorTopView) findViewById(R.id.rl_top_tab);
        if(indicatorTopView != null) {
            indicatorTopView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            indicatorHeight = indicatorTopView.getMeasuredHeight();
        }
    }

    /**
     * 顶部提示栏的个数，必须大于0
     * @return
     */
    protected abstract int getTabViewCount();

    /**
     * 布局layout的资源id
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 上层可以对ToolBar初始化
     */
    protected abstract void initToolBar();

}
