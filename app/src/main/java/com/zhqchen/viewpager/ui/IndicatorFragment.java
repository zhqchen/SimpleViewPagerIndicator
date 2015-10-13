package com.zhqchen.viewpager.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.zhqchen.viewpager.R;
import com.zhqchen.viewpager.SimpleIndicatorApplication;

/**
 * Created by zhqchen
 */
public class IndicatorFragment extends Fragment {

    @InjectView(R.id.ll_content)
    LinearLayout llContent;

    public IndicatorFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager_item, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        for(int i = 0; i < 30; i ++) {
            TextView textView = new TextView(getActivity());
            textView.setPadding(30, 30, 30, 30);
            textView.setGravity(Gravity.LEFT);
            textView.setTextColor(ContextCompat.getColor(SimpleIndicatorApplication.getContext(), R.color.common_text_color_zs1_primary));
            textView.setText("item-->" + i);
            llContent.addView(textView);
        }
    }
}
