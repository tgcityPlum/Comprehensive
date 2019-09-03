# media library

## introduction
the library provides media recording and media playing. 

## using
1\. xml code
```aidl
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#fff"
    tools:context=".RecordingActivity">

    <SurfaceView
        android:id="@+id/sv_video"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:layout_marginBottom="100dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <com.tgcity.testrecordvideo1.view.MediaRecorderButton
        android:id="@+id/btn_record"
        android:layout_width="0dp"
        android:layout_height="100dp"
        android:background="#000"
        android:text="按住拍"
        android:textColor="#fff"
        android:textSize="18sp"
        android:gravity="center"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent" />
</androidx.constraintlayout.widget.ConstraintLayout>
``` 

2\. activity code
```aidl
public class RecordingActivity extends AppCompatActivity implements MediaRecorderButton.OnRecordingFinishListener {

    private SurfaceView surfaceView;
    private MediaRecorderButton mediaRecorderButton;
    private MediaRecorderManage mediaRecorderManage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        surfaceView = findViewById(R.id.sv_video);
        mediaRecorderButton = findViewById(R.id.btn_record);
        mediaRecorderManage = MediaRecorderManage.getInstance();

        mediaRecorderButton.setFinishListener(this);
        mediaRecorderButton.setLongRecordTime(7f);
        mediaRecorderManage.setSurfaceHolder(surfaceView.getHolder());
    }

    @Override
    public void onRecordingFinish(float seconds, String fileName) {
        Log.e(getLocalClassName(), "onRecordingFinish");
        Intent intent = new Intent();
        intent.putExtra("fileName", fileName);
        setResult(MainActivity.PLAY_CODE, intent);
        finish();
    }

}
```
3\. player code
```aidl
 MediaPlayerManager mediaPlayerManager = new MediaPlayerManager();
 Bundle bundle = new Bundle();
 bundle.putString("filePath",fileName);
 mediaPlayerManager.setArguments(bundle);
 mediaPlayerManager.show(getSupportFragmentManager(),"mediaPlayerDialog");
```