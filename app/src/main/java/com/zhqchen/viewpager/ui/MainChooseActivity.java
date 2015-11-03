package com.zhqchen.viewpager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.zhqchen.viewpager.R;

/**
 * Created by chen on 15/11/2.
 */
public class MainChooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_choose);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.tv_icon_pager)
    public void onIconPagerClick(View view) {
        Intent intent = new Intent(this, IconsViewPagerActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tv_title_pager)
    public void onTitlePagerClick(View view) {
        Intent intent = new Intent(this, TitleViewPagerActivity.class);
        startActivity(intent);
    }

}
