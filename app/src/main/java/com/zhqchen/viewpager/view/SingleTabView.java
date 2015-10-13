package com.zhqchen.viewpager.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.zhqchen.viewpager.R;

/**
 * Created by zhqchen on 15/8/2.
 */
public class SingleTabView extends LinearLayout {

    private TextView tvTabTitle;
    private View verticalLine;

    private int index;

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

        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);

        verticalLine = findViewById(R.id.v_vertical_line);
        tvTabTitle = (TextView) findViewById(R.id.tv_tab_title);
    }

    public TextView getTvTabTitle() {
        return tvTabTitle;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public View getVerticalLine() {
        return verticalLine;
    }
}
