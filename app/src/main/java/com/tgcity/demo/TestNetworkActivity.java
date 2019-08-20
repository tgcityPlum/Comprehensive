package com.tgcity.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tgcity.base.network.bean.result.HttpResult;
import com.tgcity.network.callback.SimpleCallBack;
import com.tgcity.network.retrofit.NetworkRetrofitUtils;
import com.tgcity.resource.bean.response.TestDataItemBean;

import java.util.List;

public class TestNetworkActivity extends AppCompatActivity {

    private Button tvNetwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_network);


        tvNetwork = findViewById(R.id.tv_network);
        tvNetwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoadData();
            }
        });
    }

    private void onLoadData() {
        String token = "33b52682-5b13-4811-965f-7889d754f3f9";
        NetworkRetrofitUtils.getInstance().getTestList(token, new SimpleCallBack<HttpResult<List<TestDataItemBean>>>() {
            @Override
            public void onError(Throwable e) {
                Log.e(getLocalClassName(), "onError");
            }

            @Override
            public void onNext(HttpResult<List<TestDataItemBean>> result) {
                Log.e(getLocalClassName(), "onNext");
            }
        });
    }

}
