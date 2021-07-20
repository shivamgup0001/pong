package com.example.pong.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.pong.R;

import static android.content.Context.MODE_PRIVATE;

public class custom_view extends View {

    private Rect rect;
    private Rect rect1;
    private Paint paint;
    private Paint paint1;
    private Paint paint3;
    private int counter=0;
    private int x1=6;
    private int y1=6;
    private int check=0;
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
        paint3=new Paint();

        paint3.setColor(Color.WHITE);
        paint3.setTextSize(80);
        paint3.setStyle(Paint.Style.FILL);

    }


    @Override
    protected void onDraw (Canvas canvas) {
        if (counter == 0) {
            rect.left = getWidth() / 2 - 150;
            rect.top = getHeight() - 40;
            rect.right = rect.left + 300;
            rect.bottom = getHeight();
            rect1.left = getWidth() / 2 - 20;
            rect1.right = rect1.left + 50;
            rect1.top = getHeight() / 2 - 20;
            rect1.bottom = rect1.top + 50;
        }

       // ball(canvas);
        canvas.drawColor(Color.BLACK);
        canvas.drawRect(rect, paint);
        canvas.drawRect(rect1, paint1);
        canvas.drawText("Score:" + score, canvas.getWidth()/3+20, canvas.getHeight()/14, paint3);
        if (rect1.left < 0 || rect1.right >= canvas.getWidth())
            x1 *= -1;
        if (rect1.top < 0)
            y1*=-1;
        if((rect1.top>=(canvas.getHeight()-80))&&(rect1.left>=rect.left&&rect1.right<=rect.right))
        {
            y1*=-1;
            score++;
        }
        if(rect1.top>=canvas.getHeight()-60)
        {
            check=1;
            SharedPreferences sh = getContext().getSharedPreferences("pong", Context.MODE_PRIVATE);
            int a = sh.getInt("highscore", 0);
            if(a<score) {
                SharedPreferences.Editor myEdit = sh.edit();
                myEdit.putInt("highscore", score);
                canvas.drawText("High Score:" +score, canvas.getWidth()/4, canvas.getHeight()/6, paint3);
                myEdit.apply();
            }
            else
            {
                canvas.drawText("High Score:" + a, canvas.getWidth()/4, canvas.getHeight()/6, paint3);
            }

        }

        rect1.left += x1;
        rect1.right = rect1.left + 50;
        rect1.top += y1;
        rect1.bottom = rect1.top + 50;
        if(check==0)
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
if(check==0)
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

