package com.github.zqhcxy.picutil.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;

import com.github.zqhcxy.picutil.R;
import com.github.zqhcxy.picutil.util.Utils;

/**
 * 前置
 */
public class FrontCameraActivity extends AppCompatActivity {


    private LinearLayout p_ly;
    Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_camera);
        findView();
        utils=new Utils(FrontCameraActivity.this,(SurfaceView)findViewById(R.id.sfv),1);

    }

    private void findView() {
        p_ly = (LinearLayout) findViewById(R.id.p_ly);
        p_ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utils.tackPic();
            }
        });
    }

    @Override
    protected void onDestroy() {
        utils.destroy();
        super.onDestroy();
    }
}
