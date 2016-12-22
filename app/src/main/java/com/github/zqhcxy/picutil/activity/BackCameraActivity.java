package com.github.zqhcxy.picutil.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;

import com.github.zqhcxy.picutil.R;
import com.github.zqhcxy.picutil.util.Utils;

/**
 * 后置
 */
public class BackCameraActivity extends AppCompatActivity {
    private LinearLayout p_ly;
    Utils utils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_chome);
        findView();
        utils=new Utils(BackCameraActivity.this,(SurfaceView)findViewById(R.id.sfv),0);
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
