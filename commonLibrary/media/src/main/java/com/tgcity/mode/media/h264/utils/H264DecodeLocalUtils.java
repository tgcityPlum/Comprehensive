package com.tgcity.mode.media.h264.utils;

import android.content.Context;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.SurfaceHolder;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * Created by TGCit
 * on 2019/9/5.
 */

public class H264DecodeLocalUtils {

    private Context mContext;
    private SurfaceHolder mSurfaceHolder;
    private MediaCodec mMediaCodec;
    /**
     * 文件输入流
     */
    private DataInputStream mDataInputStream;

    private boolean mStopFlag = false;

    public H264DecodeLocalUtils(@NonNull Context context, @NonNull SurfaceHolder surfaceHolder) {
        mContext = context;
        mSurfaceHolder = surfaceHolder;
    }

    public void onDecodeLocalFile(String fileName) {

        File file = new File(fileName);

        if (!file.exists() || file.length() == 0) {
            Toast.makeText(mContext, "视频文件不存在", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            mDataInputStream = new DataInputStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();

            if (mDataInputStream != null) {
                try {
                    mDataInputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        mSurfaceHolder.addCallback(new SurfaceHolder.Callback() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                try {
                    //通过多媒体格式名创建一个可用的解码器
                    mMediaCodec = MediaCodec.createDecoderByType("video/avc");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //初始哈编码器
                MediaFormat mediaFormat = MediaFormat.createVideoFormat("video/avc", 1920, 1080);
                //设置帧率
                mediaFormat.setInteger(MediaFormat.KEY_FRAME_RATE, 15);

                mMediaCodec.configure(mediaFormat, holder.getSurface(), null, 0);

                startDeCordingThread();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                mMediaCodec.stop();
                mMediaCodec.release();
            }
        });
    }

    private void startDeCordingThread() {
        mMediaCodec.start();
        Thread mDecodeThread = new Thread(new DecodeThread());
        mDecodeThread.start();
    }

    private class DecodeThread implements Runnable {

        @Override
        public void run() {
            decodeLoop();
        }

        private void decodeLoop() {
            //存放目标文件的数据
            ByteBuffer[] inputBuffers = mMediaCodec.getInputBuffers();
            //解码后的数据，包含每一个buffer的元数据信息，例如偏差，在相关解码器中有效的数据大小
            MediaCodec.BufferInfo info = new MediaCodec.BufferInfo();

            long startMs = System.currentTimeMillis();
            long timeoutUs = 10000;
            byte[] marker0 = new byte[]{0, 0, 0, 1};
            byte[] dummyFrame = new byte[]{0x00, 0x00, 0x01, 0x20};
            byte[] streamBuffer = null;

            try {
                streamBuffer = getBytes(mDataInputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }

            int bytes_cnt = 0;
            while (!mStopFlag) {
                bytes_cnt = streamBuffer.length;
                if (bytes_cnt == 0) {
                    streamBuffer = dummyFrame;
                }

                int startIndex = 0;
                int remaining = bytes_cnt;
                while (true) {
                    if (remaining == 0 || startIndex >= remaining) {
                        break;
                    }
                    int nextFrameStart = KMPMatch(marker0, streamBuffer, startIndex + 2, remaining);
                    if (nextFrameStart == -1) {
                        nextFrameStart = remaining;
                    }

                    int inIndex = mMediaCodec.dequeueInputBuffer(timeoutUs);
                    if (inIndex >= 0) {
                        ByteBuffer byteBuffer = inputBuffers[inIndex];
                        byteBuffer.clear();
                        byteBuffer.put(streamBuffer, startIndex, nextFrameStart - startIndex);
                        mMediaCodec.queueInputBuffer(inIndex, 0, nextFrameStart - startIndex, 0, 0);
                        startIndex = nextFrameStart;
                    } else {
                        continue;
                    }

                    int outIndex = mMediaCodec.dequeueOutputBuffer(info, timeoutUs);

                    if (outIndex >= 0) {
                        while (info.presentationTimeUs / 1000 > System.currentTimeMillis() - startMs) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        boolean doRender = info.size != 0;
                        mMediaCodec.releaseOutputBuffer(outIndex, doRender);
                    }
                }
                mStopFlag = true;
            }
        }
    }

    private static byte[] getBytes(InputStream is) throws IOException {
        int len;
        int size = 1024;
        byte[] buf;
        if (is instanceof ByteArrayInputStream) {
            size = is.available();
            buf = new byte[size];
            len = is.read(buf, 0, size);
        } else {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            buf = new byte[size];
            while ((len = is.read(buf, 0, size)) != -1) {
                bos.write(buf, 0, len);
            }
            buf = bos.toByteArray();
        }
        return buf;
    }

    private int KMPMatch(byte[] pattern, byte[] bytes, int start, int remain) {
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int[] lsp = computeLspTable(pattern);

        int j = 0;
        for (int i = start; i < remain; i++) {
            while (j > 0 && bytes[i] != pattern[j]) {
                j = lsp[j - 1];
            }
            if (bytes[i] == pattern[j]) {
                j++;
                if (j == pattern.length) {
                    return i - (j - 1);
                }
            }
        }
        return -1;
    }

    private int[] computeLspTable(byte[] pattern) {
        int[] lsp = new int[pattern.length];
        lsp[0] = 0;
        for (int i = 1; i < pattern.length; i++) {
            int j = lsp[i - 1];
            while (j > 0 && pattern[i] != pattern[j]) {
                j = lsp[j - 1];
            }
            if (pattern[i] == pattern[j]) {
                j++;
            }
            lsp[i] = j;
        }
        return lsp;
    }

    public void onDestroy() {
        mMediaCodec.stop();
        mMediaCodec.release();
        mMediaCodec = null;
    }

}
