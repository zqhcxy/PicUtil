package com.github.zqhcxy.picutil.util;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceView;

import com.github.zqhcxy.picutil.activity.BackGroundVideoActivity;

import java.io.File;

/**
 * Created by zqh on 2016/12/22.
 */
public class VideoUtil {

    private Context mContext;

    private  String filepath = "/.ad";

    // 系统的视频文件
    private File videoFile;
    private MediaRecorder mRecorder;
    // 显示视频预览的SurfaceView
    private SurfaceView sView;
    // 记录是否正在进行录制
    private boolean isRecording = false;

    private VideorecordingInf mVideorecordingInf;

    public interface VideorecordingInf {
        void recordingState(boolean isRecording);
    }


    public VideoUtil(Context context, SurfaceView surfaceView) {
        mContext = context;
        if (surfaceView != null) {
            sView = surfaceView;
            initView();
        }
    }

    private void initView() {
        // 设置分辨率
        sView.getHolder().setFixedSize(1280, 720);
        // 设置该组件让屏幕不会自动关闭
        sView.getHolder().setKeepScreenOn(true);
    }

    public void setmVideorecordingInf(VideorecordingInf mVideorecordingInf) {
        this.mVideorecordingInf = mVideorecordingInf;
    }

    public void statrtRecording() {
        Log.i(BackGroundVideoActivity.TAG,"statrtRecording");
        if (!Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            Log.i(BackGroundVideoActivity.TAG,"SD卡不存在，请插入SD卡");
            return;
        }
        stopRecording();
        try {
            filepath=Environment
                    .getExternalStorageDirectory()
                    .getCanonicalFile()+"/.ad";
            // 创建保存录制视频的视频文件
            File file=new File(filepath);
            if(!file.exists() && !file.isDirectory()){
                file.mkdir();
            }
            videoFile = new File(filepath +"/"+System.currentTimeMillis() + ".mp4");


            // 创建MediaPlayer对象
            mRecorder = new MediaRecorder();
            mRecorder.reset();
            // 设置从麦克风采集声音(或来自录像机的声音AudioSource.CAMCORDER)
            mRecorder.setAudioSource(MediaRecorder
                    .AudioSource.MIC);
            // 设置从摄像头采集图像
            mRecorder.setVideoSource(MediaRecorder
                    .VideoSource.CAMERA);

            //设置编码比特率,不设置会使视频图像模糊
            mRecorder.setVideoEncodingBitRate(5*1024*1024);

            // 必须在设置声音编码格式、图像编码格式之前设置
            mRecorder.setOutputFormat(MediaRecorder
                    .OutputFormat.MPEG_4);
            // 设置声音编码的格式
            mRecorder.setAudioEncoder(MediaRecorder
                    .AudioEncoder.AMR_NB);
            // 设置图像编码的格式
            mRecorder.setVideoEncoder(MediaRecorder
                    .VideoEncoder.H264);
            mRecorder.setVideoSize(1280, 720);

//            mRecorder.setVideoSize(720, 720);
            //设置前后置摄像头
//            Camera comera=Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
//            mRecorder.setCamera(comera);

            // 每秒 4帧
//            mRecorder.setVideoFrameRate(20);
            mRecorder.setMaxDuration(30000);
            mRecorder.setOutputFile(videoFile.getAbsolutePath());
            // 指定使用SurfaceView来预览视频
            mRecorder.setPreviewDisplay(sView
                    .getHolder().getSurface());  //①
            mRecorder.prepare();
            // 开始录制
            mRecorder.start();
            Log.i(BackGroundVideoActivity.TAG,"statrtRecording");
            setRecordingState(true);
            isRecording = true;
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(BackGroundVideoActivity.TAG,"error: "+e.getMessage());
        }
    }

    public void stopRecording() {
        // 如果正在进行录制
        if (isRecording) {
            Log.i(BackGroundVideoActivity.TAG,"stopRecording");
            try {
                // 停止录制
                mRecorder.stop();
                // 释放资源
                mRecorder.release();
                mRecorder = null;
                isRecording = false;
                setRecordingState(false);
            }catch (Exception e){
                Log.i(BackGroundVideoActivity.TAG,"error: "+e.getMessage());
            }

        }
    }

    private void setRecordingState(boolean isRecording) {
        if (mVideorecordingInf != null) {
            mVideorecordingInf.recordingState(isRecording);
        }
    }

    public boolean isRecording() {
        return isRecording;
    }

    public void destroy() {
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }
    }
}
