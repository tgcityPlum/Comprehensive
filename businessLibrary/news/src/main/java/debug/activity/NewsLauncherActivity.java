package debug.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tgcity.mode.news.testRefreshView.TestRefreshViewCommonActivity;

/**
 * news模块--启动页面
 */
public class NewsLauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(this, TestRefreshViewCommonActivity.class));
        finish();
    }

}
