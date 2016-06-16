package com.example.climbachiya.customviewdemo;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.example.mycustomview.CircularView;


public class MainActivity extends AppCompatActivity {

    CircularView circularView = null;
    boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circularView = (CircularView) findViewById(R.id.custom_view);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void onDrawMe(View view){

        if(flag){
            circularView.setCircleColor(ContextCompat.getColor(this, R.color.colorVine));
            circularView.setLabelColor(ContextCompat.getColor(this, R.color.colorCement));
            circularView.setCircleText("Welcome...1");
            circularView.setLabelSize(40);
        }else{
            circularView.setCircleColor(ContextCompat.getColor(this, R.color.colorFacebook));
            circularView.setLabelColor(ContextCompat.getColor(this, R.color.colorCement));
            circularView.setCircleText("Welcome...2");
            circularView.setLabelSize(40);
        }

       /* // get the center for the clipping circle
        int cx = circularView.getWidth() / 2;
        int cy = circularView.getHeight() / 2;

        // get the final radius for the clipping circle
        float finalRadius = (float) Math.hypot(cx, cy);

        // create the animator for this view (the start radius is zero)
        Animator anim =
                ViewAnimationUtils.createCircularReveal(circularView, cx, cy, 0, finalRadius);

        // make the view visible and start the animation
        circularView.setVisibility(View.VISIBLE);
        anim.start();*/

        flag  = !flag;
    }
}
