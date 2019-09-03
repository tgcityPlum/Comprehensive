package com.tgcity.mode.media.manage;

import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Environment;
import android.view.SurfaceHolder;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author TGCity
 * @date 2019/9/2
 * @describe 录制视频管理
 */
public class MediaRecorderManage implements MediaRecorder.OnErrorListener, MediaRecorder.OnInfoListener {

    private static MediaRecorderManage mInstance;

    /**
     * 录制视频
     */
    private MediaRecorder mMediaRecorder;

    /**
     * 是否已准备
     */
    private boolean isPrepared = false;

    /**
     * 文件目录
     */
    private String fileDir = Environment.getExternalStorageDirectory() + "/videoRecorder";

    /**
     * 当前录制的视频地址
     */
    private String mCurrentRecordFileName;

    /**
     * 照相机
     */
    private Camera mCamera;

    private SurfaceHolder mSurfaceHolder;

    /**
     * 录制准备好的回调
     */
    private VideoPreparedListener mVideoPreparedListener;

    /**
     * 准备录制视频
     */
    public void prepareVideo() {

        try {
            isPrepared = false;
            //创建文件相关
            File file = new File(fileDir);
            if (!file.exists()) {
                file.mkdir();
            }
            String fileName = UUID.randomUUID().toString() + ".mp4";

            File mFile = new File(file, fileName);
            mCurrentRecordFileName = mFile.getAbsolutePath();
            //设置 视频录制器
            mMediaRecorder = new MediaRecorder();
            mMediaRecorder.reset();

            //设置照相机
            if (mCamera == null) {
                initCamera();
            }
            //再次录制时必须添加下面两步操作，不然会报错
            mCamera.lock();
            mCamera.unlock();


            mMediaRecorder.setCamera(mCamera);
            mMediaRecorder.setOnErrorListener(this);
            mMediaRecorder.setOnInfoListener(this);
            mMediaRecorder.setPreviewDisplay(mSurfaceHolder.getSurface());
            //设置摄像头为视频源
            mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            //设置麦克风为音频源
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            //设置视频输出为mp4
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            //设置音频格式
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            //根据分辨率设置录制尺寸
            CamcorderProfile profile = CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH);
            mMediaRecorder.setVideoSize(profile.videoFrameWidth, profile.videoFrameHeight);
            //设置视频帧频率，提高流畅度
            mMediaRecorder.setVideoFrameRate(30);
            //设置帧频率
            mMediaRecorder.setVideoEncodingBitRate(profile.videoFrameWidth * profile.videoFrameHeight);
            //输出倒转90度，保持竖屏录制
            mMediaRecorder.setOrientationHint(90);
            //录制视频格式
            mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
            mMediaRecorder.setOutputFile(mFile.getAbsolutePath());


            mMediaRecorder.prepare();
            isPrepared = true;
            mMediaRecorder.start();
            if (mVideoPreparedListener != null) {
                mVideoPreparedListener.wellPrepared();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static MediaRecorderManage getInstance(){
        if (mInstance == null){
            synchronized (MediaRecorderManage.class){
                mInstance = new MediaRecorderManage();
            }
        }
        return mInstance;
    }

    /**
     * 初始化照相机
     */
    private void initCamera() {
        releaseCameraResource();

        try {
            mCamera = Camera.open();
            mCamera.setDisplayOrientation(90);
            mCamera.setPreviewDisplay(mSurfaceHolder);
            mCamera.startPreview();
            mCamera.unlock();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放照相机
     */
    private void releaseCameraResource() {
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.lock();
            mCamera.release();
            mCamera = null;
        }
    }

    @Override
    public void onError(MediaRecorder mediaRecorder, int i, int i1) {
        if (mediaRecorder != null) {
            mediaRecorder.reset();
        }
    }

    @Override
    public void onInfo(MediaRecorder mediaRecorder, int i, int i1) {

    }

    public void setSurfaceHolder(SurfaceHolder mSurfaceHolder) {
        this.mSurfaceHolder = mSurfaceHolder;

        if (mSurfaceHolder != null) {
            initSurfaceHolder();
        }
    }

    private void initSurfaceHolder() {
        mSurfaceHolder.addCallback(mCallback);
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void setVideoPreparedListener(VideoPreparedListener mVideoPreparedListener) {
        this.mVideoPreparedListener = mVideoPreparedListener;
    }

    /**
     * 获取当前录制的绝对路径
     */
    public String getCurrentRecordFileName(){
        return mCurrentRecordFileName;
    }

    /**
     * 释放资源
     */
    public void release() {
        mMediaRecorder.stop();
        mMediaRecorder.reset();
        mMediaRecorder.release();
        mMediaRecorder = null;
    }

    /**
     * 取消录制
     * 删除录制的视频
     */
    public void cancel() {
        release();

        File file = new File(mCurrentRecordFileName);
        file.delete();
        mCurrentRecordFileName = null;
    }

    private SurfaceHolder.Callback mCallback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            initCamera();
        }

        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            releaseCameraResource();
        }
    };

    public interface VideoPreparedListener {
        void wellPrepared();
    }
}
