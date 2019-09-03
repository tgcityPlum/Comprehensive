package com.tgcity.mode.media.manage;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.tgcity.mode.media.R;

import java.io.IOException;

/**
 * @author TGCity
 * @date 2019/9/3
 * @describe 视频播放类
 */
public class MediaPlayerManager extends DialogFragment implements SurfaceHolder.Callback, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener {

    private View mView;
    private String fileName;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private MediaPlayer mediaPlayer;
    private Display display;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NORMAL, R.style.mediaPlayer_style);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        mView = inflater.inflate(R.layout.player_fragment, container);

        getArgumentsFromActivity();
        initView();
        display = getActivity().getWindowManager().getDefaultDisplay();

        WindowManager.LayoutParams layoutParams = getActivity().getWindow().getAttributes();
        layoutParams.height = display.getHeight();
        layoutParams.width = display.getWidth();

        return mView;
    }

    private void initView() {
        surfaceView = mView.findViewById(R.id.mSurfaceView);
        surfaceHolder = surfaceView.getHolder();
        surfaceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        //如果要在本页面再次播放视频，必须调用removeCallback,这里只调用一次所以不要
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

    }

    private void getArgumentsFromActivity() {
        fileName = getArguments().getString("filePath");
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        Toast.makeText(getContext(), "轻触退出", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        //必须在surface创建后才能初始化MediaPlayer，否则不会显示图像
        if (!fileName.isEmpty()) {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.reset();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDisplay(surfaceHolder);
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnPreparedListener(this);

            try {
                mediaPlayer.setDataSource(fileName);
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getContext(), "播放路径不能为空", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            //Activity销毁时停止播放，释放资源。不做这个操作，即使退出了还能播放
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        mediaPlayer = null;
    }

}
