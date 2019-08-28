package debug.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tgcity.mode.home.testweb.TestWebActivity;

/**
 * home模块--启动页面
 */
public class HomeIndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(this, TestWebActivity.class));
        finish();
    }

}
