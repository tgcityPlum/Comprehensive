/*
 * Copyright 2013-2015 duolabao.com All right reserved. This software is the
 * confidential and proprietary information of duolabao.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with duolabao.com.
 */

package com.tgcity.mode.media.h264.utils;

import android.app.Activity;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.widget.Toast;

import com.tgcity.mode.media.h264.manage.EncoderDebugger;
import com.tgcity.mode.media.h264.manage.NV21Convert;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * 类Util的实现描述 类实现描述
 *
 * @author HELONG 2016/3/8 17:42
 */
public class H264CreateCodeUtils {

    private Activity mContext;
    private String path;
    private int width = 640, height = 480;
    private int frameRate;
    private int bitrate;
    private int mCameraId;
    private MediaCodec mMediaCodec;
    private SurfaceHolder mSurfaceHolder;
    private Camera mCamera;
    private NV21Convert mConvert;

    public H264CreateCodeUtils(Activity context, SurfaceHolder surfaceHolder) {
        mContext = context;
        mSurfaceHolder = surfaceHolder;
        mCameraId = Camera.CameraInfo.CAMERA_FACING_BACK;
    }

    public void onCreateCodeFile(String fileName) {

        path = fileName;

        initMediaCodec();

        mSurfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                createCamera(holder);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
        mSurfaceHolder.setFixedSize(mContext.getResources().getDisplayMetrics().widthPixels,
                mContext.getResources().getDisplayMetrics().heightPixels);
    }

    private void initMediaCodec() {
        int dgree = getDegree();
        frameRate = 15;
        bitrate = 2 * width * height * frameRate / 20;
        EncoderDebugger debugger = EncoderDebugger.debug(mContext.getApplicationContext(), width, height);
        mConvert = debugger.getNV21Convertor();
        try {
            mMediaCodec = MediaCodec.createByCodecName(debugger.getEncoderName());
            MediaFormat mediaFormat;
            if (dgree == 0) {
                mediaFormat = MediaFormat.createVideoFormat("video/avc", height, width);
            } else {
                mediaFormat = MediaFormat.createVideoFormat("video/avc", width, height);
            }
            mediaFormat.setInteger(MediaFormat.KEY_BIT_RATE, bitrate);
            mediaFormat.setInteger(MediaFormat.KEY_FRAME_RATE, frameRate);
            mediaFormat.setInteger(MediaFormat.KEY_COLOR_FORMAT,
                    debugger.getEncoderColorFormat());
            mediaFormat.setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, 1);
            mMediaCodec.configure(mediaFormat, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE);
            mMediaCodec.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取最大支持帧率
     *
     * @param parameters Camera.Parameters
     * @return int[]
     */
    private static int[] getMaxSupportedFrameRate(Camera.Parameters parameters) {
        int[] maxFps = new int[]{0, 0};
        List<int[]> supportedFpsRanges = parameters.getSupportedPreviewFpsRange();
        for (int[] interval : supportedFpsRanges) {
            if (interval[1] > maxFps[1] || (interval[0] > maxFps[0] && interval[1] == maxFps[1])) {
                maxFps = interval;
            }
        }
        return maxFps;
    }

    /**
     * create Camera
     *
     * @param surfaceHolder SurfaceHolder
     * @return boolean
     */
    private boolean createCamera(SurfaceHolder surfaceHolder) {
        try {
            mCamera = Camera.open(mCameraId);
            Camera.Parameters parameters = mCamera.getParameters();
            int[] max = getMaxSupportedFrameRate(parameters);
            Camera.CameraInfo camInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(mCameraId, camInfo);
            int cameraRotationOffset = camInfo.orientation;
            int rotate = (360 + cameraRotationOffset - getDegree()) % 360;
            parameters.setRotation(rotate);
            parameters.setPreviewFormat(ImageFormat.NV21);
//            List<Camera.Size> sizes = parameters.getSupportedPreviewSizes();
            parameters.setPreviewSize(width, height);
            parameters.setPreviewFpsRange(max[0], max[1]);
            mCamera.setParameters(parameters);
            mCamera.autoFocus(null);
            int displayRotation;
            displayRotation = (cameraRotationOffset - getDegree() + 360) % 360;
            mCamera.setDisplayOrientation(displayRotation);
            mCamera.setPreviewDisplay(surfaceHolder);
            return true;
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String stack = sw.toString();
            Toast.makeText(mContext, stack, Toast.LENGTH_LONG).show();
            onDestroy();
            e.printStackTrace();
            return false;
        }
    }

    /**
     * get degree
     *
     * @return int
     */
    private int getDegree() {
        int rotation = mContext.getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break; // Natural orientation
            case Surface.ROTATION_90:
                degrees = 90;
                break; // Landscape left
            case Surface.ROTATION_180:
                degrees = 180;
                break;// Upside down
            case Surface.ROTATION_270:
                degrees = 270;
                break;// Landscape right
        }
        return degrees;
    }

    /**
     * 开启预览
     */
    public synchronized boolean startPreview(boolean isStart) {
        if (mCamera != null && !isStart) {
            mCamera.startPreview();
            int previewFormat = mCamera.getParameters().getPreviewFormat();
            Camera.Size previewSize = mCamera.getParameters().getPreviewSize();
            int size = previewSize.width * previewSize.height
                    * ImageFormat.getBitsPerPixel(previewFormat)
                    / 8;
            mCamera.addCallbackBuffer(new byte[size]);
            mCamera.setPreviewCallbackWithBuffer(new Camera.PreviewCallback() {
                byte[] mPpsSps = new byte[0];

                @Override
                public void onPreviewFrame(byte[] data, Camera camera) {
                    if (data == null) {
                        return;
                    }
                    ByteBuffer[] inputBuffers = mMediaCodec.getInputBuffers();
                    ByteBuffer[] outputBuffers = mMediaCodec.getOutputBuffers();
                    byte[] dst = new byte[data.length];
                    Camera.Size previewSize = mCamera.getParameters().getPreviewSize();
                    if (getDegree() == 0) {
                        dst = H264CreateCodeUtils.rotateNV21Degree90(data, previewSize.width, previewSize.height);
                    } else {
                        dst = data;
                    }
                    try {
                        int bufferIndex = mMediaCodec.dequeueInputBuffer(5000000);
                        if (bufferIndex >= 0) {
                            inputBuffers[bufferIndex].clear();
                            mConvert.convert(dst, inputBuffers[bufferIndex]);
                            mMediaCodec.queueInputBuffer(bufferIndex, 0,
                                    inputBuffers[bufferIndex].position(),
                                    System.nanoTime() / 1000, 0);
                            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
                            int outputBufferIndex = mMediaCodec.dequeueOutputBuffer(bufferInfo, 0);
                            while (outputBufferIndex >= 0) {
                                ByteBuffer outputBuffer = outputBuffers[outputBufferIndex];
                                byte[] outData = new byte[bufferInfo.size];
                                outputBuffer.get(outData);
                                //记录pps和sps
                                if (outData[0] == 0 && outData[1] == 0 && outData[2] == 0 && outData[3] == 1 && outData[4] == 103) {
                                    mPpsSps = outData;
                                } else if (outData[0] == 0 && outData[1] == 0 && outData[2] == 0 && outData[3] == 1 && outData[4] == 101) {
                                    //在关键帧前面加上pps和sps数据
                                    byte[] iframeData = new byte[mPpsSps.length + outData.length];
                                    System.arraycopy(mPpsSps, 0, iframeData, 0, mPpsSps.length);
                                    System.arraycopy(outData, 0, iframeData, mPpsSps.length, outData.length);
                                    outData = iframeData;
                                }
                                H264CreateCodeUtils.save(outData, 0, outData.length, path, true);
                                mMediaCodec.releaseOutputBuffer(outputBufferIndex, false);
                                outputBufferIndex = mMediaCodec.dequeueOutputBuffer(bufferInfo, 0);
                            }
                        } else {
                            Log.e("easypusher", "No buffer available !");
                        }
                    } catch (Exception e) {
                        StringWriter sw = new StringWriter();
                        PrintWriter pw = new PrintWriter(sw);
                        e.printStackTrace(pw);
                        String stack = sw.toString();
                        Log.e("save_log", stack);
                        e.printStackTrace();
                    } finally {
                        mCamera.addCallbackBuffer(dst);
                    }
                }

            });
            return true;
        } else {
            return isStart;
        }
    }

    /**
     * 停止预览
     */
    public synchronized boolean stopPreview(boolean isStart) {
        if (mCamera != null && isStart) {
            mCamera.stopPreview();
            mCamera.setPreviewCallbackWithBuffer(null);
            return false;
        } else {
            return isStart;
        }
    }

    /**
     * 将YUV420SP数据顺时针旋转90度
     *
     * @param data        要旋转的数据
     * @param imageWidth  要旋转的图片宽度
     * @param imageHeight 要旋转的图片高度
     * @return 旋转后的数据
     */
    public static byte[] rotateNV21Degree90(byte[] data, int imageWidth, int imageHeight) {
        byte[] yuv = new byte[imageWidth * imageHeight * 3 / 2];
        // Rotate the Y luma
        int i = 0;
        for (int x = 0; x < imageWidth; x++) {
            for (int y = imageHeight - 1; y >= 0; y--) {
                yuv[i] = data[y * imageWidth + x];
                i++;
            }
        }
        // Rotate the U and V color components
        i = imageWidth * imageHeight * 3 / 2 - 1;
        for (int x = imageWidth - 1; x > 0; x = x - 2) {
            for (int y = 0; y < imageHeight / 2; y++) {
                yuv[i] = data[(imageWidth * imageHeight) + (y * imageWidth) + x];
                i--;
                yuv[i] = data[(imageWidth * imageHeight) + (y * imageWidth) + (x - 1)];
                i--;
            }
        }
        return yuv;
    }

    /**
     * 保存数据到本地
     *
     * @param buffer 要保存的数据
     * @param offset 要保存数据的起始位置
     * @param length 要保存数据长度
     * @param path   保存路径
     * @param append 是否追加
     */
    public static void save(byte[] buffer, int offset, int length, String path, boolean append) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path, append);
            fos.write(buffer, offset, length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 销毁Camera
     */
    public synchronized void onDestroy() {
        if (mCamera != null) {
            mCamera.stopPreview();
            try {
                mCamera.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
            mCamera = null;
        }

        if (mMediaCodec != null) {
            mMediaCodec.stop();
            mMediaCodec.release();
            mMediaCodec = null;
        }
    }

}
