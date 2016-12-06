package com.github.zqhcxy.picutil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button back_btn;
    private Button front_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
    }

    private void findView() {
        back_btn = (Button) findViewById(R.id.back_btn);
        front_btn = (Button) findViewById(R.id.front_btn);

        back_btn.setOnClickListener(this);
        front_btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_btn:
                Intent intent = new Intent(MainActivity.this, BackCameraActivity.class);
                startActivity(intent);
                break;
            case R.id.front_btn:
                Intent intent1 = new Intent(MainActivity.this, FrontCameraActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
