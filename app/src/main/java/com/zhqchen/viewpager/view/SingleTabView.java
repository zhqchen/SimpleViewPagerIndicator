package com.zhqchen.viewpager.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.zhqchen.viewpager.R;

/**
 * Created by zhqchen on 15/8/2.
 */
public class SingleTabView extends LinearLayout {

    private TextView tvTabTitle;
    private ImageView ivTitleIcon;

    private int index;
    private int padding = 0;

    public SingleTabView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.view_single_tab_text, this);
        initLayout();
    }

    public SingleTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_single_tab_text, this);
        initLayout();
    }

    private void initLayout() {
        LayoutParams params = new LayoutParams(0 , ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        setLayoutParams(params);//必须要先配置param
        int padding = getContext().getResources().getDimensionPixelSize(R.dimen.tab_view_inner_padding);
        setPadding(0, padding, 0, padding);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);

        tvTabTitle = (TextView) findViewById(R.id.tv_tab_title);
        ivTitleIcon = (ImageView) findViewById(R.id.iv_title_icon);
    }

    public TextView getTvTabTitle() {
        return tvTabTitle;
    }

    public ImageView getIvTitleIcon() {
        return ivTitleIcon;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
