package com.github.zqhcxy.picutil.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.github.zqhcxy.picutil.R;
import com.github.zqhcxy.picutil.util.VideoUtil;

public class BackGroundVideoActivity extends AppCompatActivity {

    private SurfaceView video_sfv;
    private VideoUtil videoUtil;

    public static final String TAG="VideoTest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_ground_video);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.RED);

        video_sfv = (SurfaceView) findViewById(R.id.video_sfv);
        LinearLayout p_ly = (LinearLayout) findViewById(R.id.p_ly);

        videoUtil = new VideoUtil(this, video_sfv);
        videoUtil.setmVideorecordingInf(new VideoUtil.VideorecordingInf() {
            @Override
            public void recordingState(boolean isRecording) {
                Log.i(TAG,"recordingState: "+isRecording);
                if (isRecording) {
                    getWindow().setStatusBarColor(Color.GREEN);
                } else {
                    getWindow().setStatusBarColor(Color.RED);
                }
            }
        });

        p_ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"onclick recordingState: "+videoUtil.isRecording());
                if (videoUtil.isRecording()) {
                    videoUtil.stopRecording();
                } else {
                    videoUtil.statrtRecording();
                }

            }
        });

    }

    @Override
    protected void onDestroy() {
        if(videoUtil!=null){
            videoUtil.destroy();
        }
        super.onDestroy();
    }
}
