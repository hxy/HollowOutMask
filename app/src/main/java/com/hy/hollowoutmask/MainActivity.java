package com.hy.hollowoutmask;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //页面延伸到状态栏下展示
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        //设置状态栏透明
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        findViewById(R.id.tv_screenshot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMaskScreenshot(view);
            }
        });

        findViewById(R.id.tv_hollow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMaskHole(view);
            }
        });
    }





    private View maskView;
    private void addMaskScreenshot(View targetView){
        maskView = new FrameLayoutWithScreenshot(this, 0xa00b0b0b, targetView);
        maskView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    removeMask();
                    return true;
                }
                return false;
            }
        });

        ((ViewGroup)findViewById(android.R.id.content)).addView(maskView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    private void addMaskHole(View targetView){
        maskView = new FrameLayoutWithHole(this, 0xa00b0b0b, targetView);
        maskView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    removeMask();
                    return true;
                }
                return false;
            }
        });

        ((ViewGroup)findViewById(android.R.id.content)).addView(maskView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }


    private void removeMask() {
        ((ViewGroup)findViewById(android.R.id.content)).removeView(maskView);
    }
}