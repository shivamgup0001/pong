package com.example.pong.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.pong.R;

public class custom_view extends View {

    private Rect rect;
    private Rect rect1;
    private Paint paint;
    private Paint paint1;
    private int counter=0;
    private Bitmap bmp;
    private int x1=6;
    private int y1=6;
    private int score=0;



    public custom_view(Context context) {
        super(context);

                init(null);
    }

    public custom_view(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init (attrs);
    }

    public custom_view(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init (attrs);
    }

    public custom_view(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init (attrs);
    }
    private void init (@Nullable AttributeSet set){

        rect=new Rect();
        rect1=new Rect();
        paint =new Paint();
paint1=new Paint();
        paint.setColor(Color.GREEN);
paint1.setColor(Color.YELLOW);

    }
    @Override
    protected void onDraw (Canvas canvas) {
        if (counter == 0) {
            rect.left = getWidth() / 2 - 150;
            rect.top = getHeight() - 50;
            rect.right = rect.left + 300;
            rect.bottom = getHeight();
            rect1.left = getWidth() / 2 - 20;
            rect1.right = rect1.left + 40;
            rect1.top = getHeight() / 2 - 20;
            rect1.bottom = rect1.top + 40;
        }

        ball(canvas);



        canvas.drawColor(Color.BLACK);
        canvas.drawRect(rect, paint);
        canvas.drawRect(rect1, paint1);


    }
    public void ball(Canvas c)
    {

        if (rect1.left < 0 || rect1.right >= c.getWidth())
            x1 *= -1;
        if (rect1.top < 0)
            y1*=-1;
        if(rect1.left>=rect.left&&rect1.right<=rect.right) {
            if (rect1.bottom >= (c.getHeight() - 50)) {
                y1 *= -1;
                score++;
                if(rect1.bottom>=c.getHeight())
                    c.drawColor(Color.WHITE);
            }
        }
        if(rect1.bottom>=c.getHeight())
            c.drawColor(Color.WHITE);




        rect1.left += x1;
        rect1.right = rect1.left + 40;
        rect1.top += y1;
        rect1.bottom = rect1.top + 40;

        postInvalidate();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        boolean value= super.onTouchEvent(event);

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            {

                return true;
            }
            case MotionEvent.ACTION_MOVE:
            {

                float x=event.getX();

if(x>150&&x<(getWidth()-150)) {
    if (x > rect.left && x < rect.right) {//touched
        rect.left = (int) x - 150;
        rect.top = getHeight() - 50;
        rect.right = rect.left + 300;
        rect.bottom = getHeight();
        counter++;

        postInvalidate();
        return true;
    }
}
                return value;
            }

        }
        return value;
    }

}
