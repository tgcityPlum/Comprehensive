package debug.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tgcity.base.interfaces.RouteNavigationCallBack;
import com.tgcity.base.utils.RouteIntentUtils;


/**
 * @author TGCity
 * web mode launcher activity
 */
public class WebLauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RouteIntentUtils.toJumpWebModeIndexActivity(getApplicationContext(), new RouteNavigationCallBack() {
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
