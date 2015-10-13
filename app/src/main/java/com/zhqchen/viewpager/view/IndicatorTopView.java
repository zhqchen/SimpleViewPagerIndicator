package com.zhqchen.viewpager.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.zhqchen.viewpager.R;

/**
 * 顶部indicator指示栏的view，目前仅支持平分屏幕宽度的类型；
 * Created by zhqchen
 */
public class IndicatorTopView extends RelativeLayout {

    @InjectView(R.id.ll_tip_header)
    protected LinearLayout llTipHeader;

    private int singleViewTextSize = 16;//单个view的提示文本的字体大小(sp), 可配置
    private int singleViewTextColor = -1;//单个view的提示文本的初始颜色的id, 可配置
    private int singleViewTextHighlightColor = -1;//单个view的提示文本的高亮颜色的id, 可配置
    private int singleTabBackground = -1;

    private ViewPager mViewPager;

    private boolean isCanVerticalScroll;//是否可以垂直滑动,默认不可滑动，可配置

    public IndicatorTopView(Context context) {
        this(context, null);
    }

    public IndicatorTopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_indicator_top_layout, this);
        setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        initViewsParameters(context, attrs);
    }

    public IndicatorTopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.view_indicator_top_layout, this);
        setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        initViewsParameters(context, attrs);
    }

    private void initViewsParameters(Context context, AttributeSet attrs) {
        ButterKnife.inject(this);

        singleViewTextColor = ContextCompat.getColor(getContext(), R.color.common_text_color_zs2_secondary);
        singleViewTextHighlightColor = ContextCompat.getColor(getContext(), R.color.theme_material_accent);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.IndicatorLayoutView);
        isCanVerticalScroll = array.getBoolean(R.styleable.IndicatorLayoutView_is_can_vertical_scroll, false);
        singleViewTextSize = array.getInt(R.styleable.IndicatorLayoutView_single_view_text_size, singleViewTextSize);
        singleViewTextColor = array.getColor(R.styleable.IndicatorLayoutView_single_view_text_color, singleViewTextColor);//ok
        singleViewTextHighlightColor = array.getColor(R.styleable.IndicatorLayoutView_single_view_text_highlight_color, singleViewTextHighlightColor);//ok
        singleTabBackground = array.getResourceId(R.styleable.IndicatorLayoutView_single_tab_background, R.drawable.selector_indicator_text_bg_default);//ok
        array.recycle();
        initLayout();
    }

    private void initLayout() {
        llTipHeader.setOrientation(LinearLayout.HORIZONTAL);
        addSingleView(0, "Tab View", 1);//加一个默认tab，使得 KDIndicatorFragmentActivity 可以首先获取到其高度
    }

    public void setViewPager(ViewPager pager) {
        if (mViewPager == pager) {
            return;
        }
        final PagerAdapter adapter = pager.getAdapter();
        if (adapter == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        mViewPager = pager;
        notifyDataSetChanged();
    }

    public void notifyDataSetChanged() {
        llTipHeader.removeAllViews();
        PagerAdapter adapter = mViewPager.getAdapter();
        int count = adapter.getCount();
        for(int i = 0; i < count; i++) {
            CharSequence title = adapter.getPageTitle(i);
            if (title == null) {
                title = "";
            }
            addSingleView(i, title, count);
        }
    }

    private void addSingleView(int index, CharSequence title, int count) {

        final SingleTabView tabView = new SingleTabView(getContext());
        tabView.getTvTabTitle().setTextSize(TypedValue.COMPLEX_UNIT_SP, singleViewTextSize);
        tabView.getTvTabTitle().setText(title);
        tabView.setIndex(index);
        tabView.setBackgroundResource(singleTabBackground);
        tabView.setOnClickListener(mTabClickListener);

        if(index == 0) {
            tabView.getTvTabTitle().setTextColor(singleViewTextHighlightColor);
        } else {
            tabView.getTvTabTitle().setTextColor(singleViewTextColor);
        }

        if(index == count - 1) {
            tabView.getVerticalLine().setVisibility(View.GONE);
        }

        llTipHeader.addView(tabView);
    }

    public void setCurrentItem(int item,boolean isSmoothScroll) {
        if (mViewPager == null) {
            throw new IllegalStateException("ViewPager has not been found.");
        }
        mViewPager.setCurrentItem(item, isSmoothScroll);
    }

    public void addOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        mViewPager.addOnPageChangeListener(listener);
    }

    public void changeOperationTextViews(int position) {
        SingleTabView tabView;
        for (int i = 0; i < mViewPager.getChildCount(); i++) {
            tabView = (SingleTabView) llTipHeader.getChildAt(i);
            if (i == position) {
                tabView.getTvTabTitle().setTextColor(singleViewTextHighlightColor);
            } else {
                tabView.getTvTabTitle().setTextColor(singleViewTextColor);
            }
        }
    }

    private final OnClickListener mTabClickListener = new OnClickListener() {
        public void onClick(View view) {
            SingleTabView tabView = (SingleTabView)view;
            final int oldSelected = mViewPager.getCurrentItem();
            final int newSelected = tabView.getIndex();
            if (oldSelected != newSelected) {
                changeOperationTextViews(newSelected);
                setCurrentItem(newSelected, true);
            }
        }
    };

    public boolean isCanVerticalScroll() {
        return isCanVerticalScroll;
    }
}
