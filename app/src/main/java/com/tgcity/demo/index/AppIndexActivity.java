package com.tgcity.demo.index;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tgcity.base.interfaces.RouteNavigationCallBack;
import com.tgcity.base.utils.RouteIntentUtils;

/**
 * @author TGCity
 * app mode index activity
 */
public class AppIndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RouteIntentUtils.onLauncherModeIndexActivity(getApplicationContext(), new RouteNavigationCallBack() {
            @Override
            public void onArrivalBack() {
                finish();
            }

            @Override
            public void onLostBack() {

            }
        });
    }
}
