package com.example.mycustomview;

import android.animation.Animator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewAnimationUtils;

/**
 * Created by C.limbachiya on 6/9/2016.
 */
public class CircularView extends View {

    //circle and text colors
    private int circleColor, labelColor, labelSize;
    //label text
    private String circleText;
    //paint for drawing custom view
    private Paint circlePaint;

    public CircularView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setupView(context, attrs);
    }

    private void setupView(Context context, AttributeSet attrs) {
        //paint object for drawing in onDraw
        circlePaint = new Paint();

        //get the attributes specified in attrs.xml using the name we included
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircularView, 0, 0);
        try {
            circleText = array.getString(R.styleable.CircularView_circleLabel);
            circleColor = array.getInteger(R.styleable.CircularView_circleColor, 0); //0 is default
            labelColor = array.getInteger(R.styleable.CircularView_labelColor, 0);
            labelSize = array.getInteger(R.styleable.CircularView_labelSize, 0);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            array.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //draw the view
        //get half of the width and height as we are working with a circle
        int viewWidthHalf = this.getMeasuredWidth()/2;
        int viewHeightHalf = this.getMeasuredHeight()/2;

        //get the radius as half of the width or height, whichever is smaller
        //subtract ten so that it has some space around it
        int radius = 0;
        if(viewWidthHalf>viewHeightHalf)
            radius = viewHeightHalf-10;
        else
            radius = viewWidthHalf-10;

        circlePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(circleColor);

        canvas.drawCircle(viewWidthHalf, viewHeightHalf, radius, circlePaint);

        //set the text color using the color specified
        circlePaint.setColor(labelColor);
        circlePaint.setTextAlign(Paint.Align.CENTER);
        circlePaint.setTextSize(labelSize);

        //set text properties
        canvas.drawText(circleText, viewWidthHalf, viewHeightHalf, circlePaint);

        // get the center for the clipping circle
        int cx = getRootView().getWidth() / 2;
        int cy = getRootView().getHeight() / 2;

        // get the final radius for the clipping circle
        float finalRadius = (float) Math.hypot(cx, cy);

        // create the animator for this view (the start radius is zero)
        Animator anim =
                null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            anim = ViewAnimationUtils.createCircularReveal(getRootView(), cx, cy, 0, finalRadius);
        }

        // make the view visible and start the animation
        getRootView().setVisibility(View.VISIBLE);
        anim.start();

    }

    public int getCircleColor(){
        return circleColor;
    }

    public int getLabelColor(){
        return labelColor;
    }

    public String getLabelText(){
        return circleText;
    }

    public int getLabelSize(){
        return labelSize;
    }

    public void setCircleColor(int newColor){
        //update the instance variable
        circleColor = newColor;
        //redraw the view
        invalidate();
        requestLayout();
    }

    public void setLabelColor(int newColor){
        //update the instance variable
        labelColor = newColor;
        //redraw the view
        invalidate();
        requestLayout();
    }

    public void setLabelSize(int newSize){
        //update the instance variable
        labelSize = newSize;
        //redraw the view
        invalidate();
        requestLayout();
    }

    public void setCircleText(String newLabel){
        //update the instance variable
        circleText = newLabel;
        //redraw the view
        invalidate();
        requestLayout();
    }
}
