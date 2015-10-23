# SimpleViewPagerIndicator
轻量级的ViewPagerIndicator，目前仅支持Tab项平分屏幕宽度的item的滑动。

##IndicatorTopView配置项：
```
    single_tab_background: 每个Tab项的背景，drawable or color，如：R.drawable.**
    single_view_text_size: 单个Tab的提示文本的字体大小(单位sp)， 如：16
    single_view_text_color: 单个Tab的提示文本的默认颜色的id，如R.color.gray
    single_view_text_highlight_color: 单个Tab的提示文本的高亮颜色的id，如R.color.white
```

##用法
1. 布局引入IndicatorTopView，需固定id为R.id.rl_top_tab, 以便后续在基类中使用：
```
<com.zhqchen.viewpager.view.IndicatorTopView
        android:id="@+id/rl_top_tab"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
```
2. 界面Activity需继承BaseIndicatorActivity，indicator滑动逻辑在BaseIndicatorActivity中封装实现。

3. IndicatorTopView绑定ViewPager，其中FragmentArrayPagerAdapter需要实现getPageTitle()，以便IndicatorTopView获取Tab提示文本：
```
    @Override
    public CharSequence getPageTitle(int position) {
        return SimpleIndicatorApplication.getContext().getResources().getString(pageTitle[position]);
    }
```
```
    mAdapter = new FragmentArrayPagerAdapter(getSupportFragmentManager());
    mViewPager.setAdapter(mAdapter);
    indicatorTopView.setViewPager(mViewPager);
```

4.IndicatorTopView设置ViewPager的滑动监听：
```
    indicatorTopView.addOnPageChangeListener(new IndicatorPageChangedListener() {});
```

##Activity参考
```
    package com.zhqchen.viewpager.ui;
    
       import android.os.Bundle;
       import android.support.v4.view.ViewPager;
       import butterknife.ButterKnife;
       import butterknife.InjectView;
       import com.zhqchen.viewpager.R;
       import com.zhqchen.viewpager.adapter.FragmentArrayPagerAdapter;
       
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
```

##FragmentPagerAdapter参考
```
    package com.zhqchen.viewpager.adapter;
    
    import android.support.v4.app.Fragment;
    import android.support.v4.app.FragmentManager;
    import android.support.v4.app.FragmentPagerAdapter;
    import com.zhqchen.viewpager.SimpleIndicatorApplication;
    
    import java.util.ArrayList;
    import java.util.Collection;
    import java.util.List;
    
    @SuppressWarnings("UnusedDeclaration")
    public class FragmentArrayPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> mItems = new ArrayList<>();
    
        private int[] pageTitle;
    
        public FragmentArrayPagerAdapter(FragmentManager fm) {
            super(fm);
        }
    
        @Override
        public Fragment getItem(int i) {
            return mItems.get(i);
        }
    
        @Override
        public int getCount() {
            return mItems.size();
        }
    
        @Override
        public CharSequence getPageTitle(int position) {
            return SimpleIndicatorApplication.getContext().getResources().getString(pageTitle[position]);
        }
    
        public void setPageTitle(int[] title) {
            pageTitle = title;
        }
    
        /**
         * Adds the specified fragment at the end of the array.
         * 需要手动notifyDataSetChanged()
         *
         * @param fragment
         */
        public void add(Fragment fragment) {
            mItems.add(fragment);
        }
    
        /**
         * Adds the specified Collection of fragments at the end of the array.
         *
         * @param fragments
         */
        public void addAll(Collection<Fragment> fragments) {
            mItems.addAll(fragments);
            notifyDataSetChanged();
        }
    
        /**
         * Adds the specified fragments at the end of the array.
         *
         * @param fragments
         */
        public void addAll(Fragment... fragments) {
            for (Fragment fragment : fragments) {
                mItems.add(fragment);
            }
            notifyDataSetChanged();
        }
    
        /**
         * Remove all elements from the list.
         */
        public void clear() {
            mItems.clear();
            notifyDataSetChanged();
        }
    
        /**
         * Inserts the specified fragment at the specified index in the array.
         *
         * @param fragment
         * @param index
         */
        public void insert(Fragment fragment, int index) {
            mItems.add(index, fragment);
            notifyDataSetChanged();
        }
    
        public void remove(Fragment fragment) {
            mItems.remove(fragment);
            notifyDataSetChanged();
        }
    
        public void removeAll() {
            mItems.clear();
            notifyDataSetChanged();
        }
    
        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }
```
