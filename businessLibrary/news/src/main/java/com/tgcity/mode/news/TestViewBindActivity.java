package com.tgcity.mode.news;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestViewBindActivity extends AppCompatActivity {

    @BindView(R2.id.tv_message)
    TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view_bind);
        ButterKnife.bind(this);

        tvMessage.setText("jeifjgjkg");
    }
}
