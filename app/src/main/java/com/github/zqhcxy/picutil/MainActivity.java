package com.github.zqhcxy.picutil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.github.zqhcxy.picutil.activity.BackCameraActivity;
import com.github.zqhcxy.picutil.activity.BackGroundVideoActivity;
import com.github.zqhcxy.picutil.activity.FrontCameraActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button back_btn;
    private Button front_btn;
    private Button video_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
    }

    private void findView() {
        back_btn = (Button) findViewById(R.id.back_btn);
        front_btn = (Button) findViewById(R.id.front_btn);
        video_btn = (Button) findViewById(R.id.video_btn);

        back_btn.setOnClickListener(this);
        front_btn.setOnClickListener(this);
        video_btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_btn:
                startIntent(BackCameraActivity.class);
                break;
            case R.id.front_btn:
                startIntent(FrontCameraActivity.class);
                break;
            case R.id.video_btn:
                startIntent(BackGroundVideoActivity.class);
                break;
        }
    }



    private void startIntent(Class object){
        Intent intent = new Intent(MainActivity.this, object);
        startActivity(intent);
    }
}
