package com.zhqchen.viewpager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.zhqchen.viewpager.SimpleIndicatorApplication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SuppressWarnings("UnusedDeclaration")
public class FragmentArrayPagerAdapter extends FragmentPagerAdapter implements IconTabAdapter {
    private List<Fragment> mItems = new ArrayList<>();

    private int[] pageTitles;

    private int[] pageTitleIcons;

    public FragmentArrayPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * 构造
     * @param fm
     * @param pageTitles 文本资源id数组，可为null
     * @param pageTitleIcons 图标资源id数组， 可为null
     */
    public FragmentArrayPagerAdapter(FragmentManager fm, int[] pageTitles, int[] pageTitleIcons) {
        super(fm);
        this.pageTitles = pageTitles;
        this.pageTitleIcons = pageTitleIcons;
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
        if(pageTitles == null || pageTitles.length == 0) {
            return null;
        }
        return SimpleIndicatorApplication.getContext().getResources().getString(pageTitles[position]);
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

    @Override
    public int getIconResId(int position) {
        if(pageTitleIcons == null || pageTitleIcons.length == 0) {
            return 0;
        }
        return pageTitleIcons[position];
    }
}