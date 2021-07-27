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
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.pong.MainActivity;
import com.example.pong.MainActivity3;
import com.example.pong.R;
import com.example.pong.SoundPlayer;

import static android.content.Context.MODE_PRIVATE;

public class custom1 extends View {

    private Rect rect;
    private Rect rect1;
    private Rect rect2;
    private Rect rect3;
    private Rect rect4;
    private Rect rect5;
    private Paint paint;
    private Paint paint1;
    private Paint paint2;
    private Paint paint3;
    private Paint paint4;
    private Paint paint5;
    private int counter=0;
    private int x1=8;
    private int y1=8;
    private int check=0;
    private int score=0;
    private int score1=0;
    private int power=0;
    private int start=0;
    //private SoundPlayer sound;
    static MediaPlayer hitsound,wallsound,oversound;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public custom1(Context context) {
        super(context);
        hitsound=new MediaPlayer();
        wallsound=new MediaPlayer();
        oversound=new MediaPlayer();
        hitsound.setAudioAttributes( new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).setUsage(AudioAttributes.USAGE_MEDIA).build());
        wallsound.setAudioAttributes( new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).setUsage(AudioAttributes.USAGE_MEDIA).build());
        oversound.setAudioAttributes( new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).setUsage(AudioAttributes.USAGE_MEDIA).build());
        hitsound=MediaPlayer.create(context,R.raw.hit);
        wallsound=MediaPlayer.create(context,R.raw.beep);
        oversound=MediaPlayer.create(context,R.raw.gameover);
        //sound=new SoundPlayer(context);
        init(null);
    }

    public custom1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init (attrs);
    }

    public custom1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init (attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public custom1(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init (attrs);
    }
    private void init (@Nullable AttributeSet set){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((MainActivity3) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;

        rect=new Rect();
        rect1=new Rect();
        rect2=new Rect();
        rect3=new Rect();
        rect4=new Rect();
        rect5=new Rect();
        paint =new Paint();
        paint1=new Paint();
        paint2=new Paint();
        paint4=new Paint();
        paint5 =new Paint();
        paint.setColor(Color.GREEN);
        paint4.setColor(Color.BLUE);
        paint1.setColor(Color.YELLOW);
        paint3=new Paint();
        paint2.setTypeface(Typeface.create("Arial",Typeface.BOLD));
        paint3.setColor(Color.WHITE);
        paint5.setColor(Color.WHITE);
        paint3.setTextSize((int)(height*0.025));
        paint5.setTextSize((int)(height*0.045));
        paint3.setStyle(Paint.Style.FILL);
        paint2.setColor(Color.YELLOW);
        paint2.setTextSize((int)(height*0.06));
        paint2.setStyle(Paint.Style.FILL);
    }


    @Override
    protected void onDraw (Canvas canvas) {
        if (counter == 0) {
            rect.left = getWidth() / 2 - 150;
            rect.top = getHeight() - 30;
            rect.right = rect.left + 300;
            rect.bottom = getHeight();
            rect3.left = getWidth() / 2 - 150;
            rect3.top = getHeight()/11+40;
            rect3.right = rect3.left + 300;
            rect3.bottom = rect3.top+30;
            rect1.left = getWidth() / 2 - 20;
            rect1.right = rect1.left + 50;
            rect1.top = getHeight() / 2 - 20;
            rect1.bottom = rect1.top + 50;
        }
        rect2.left = 0;
        rect2.top = getHeight()/11;
        rect2.right = getWidth();
        rect2.bottom = rect2.top+40;
        rect4.left=getWidth()/2-150;
        rect4.right=rect4.left+300;
        rect4.top=getHeight()/2-50;
        rect4.bottom=rect4.top+100;
        rect5.left=getWidth()/2-200;
        rect5.right=rect4.left+360;
        rect5.top=getHeight()/2-50;
        rect5.bottom=rect4.top+100;

        // ball(canvas);
        canvas.drawColor(Color.BLACK);
        canvas.drawRect(rect, paint);
        canvas.drawRect(rect3, paint4);
        canvas.drawRect(rect1, paint1);
        canvas.drawRect(rect2, paint);

        if(start==0){
            canvas.drawRect(rect4, paint4);
            canvas.drawText("START",rect4.left+30,rect4.bottom-20,paint2);
        }
        canvas.drawText("Player Score:" + score, 30, canvas.getHeight()/14, paint3);
        canvas.drawText("Computer Score:" + score1, canvas.getWidth()/2+30, canvas.getHeight()/14, paint3);
        if (rect1.left < 0 || rect1.right >= canvas.getWidth())
        {
            x1 *= -1;
            //sound.playWallSound();
            if(wallsound!=null)
                wallsound.start();
        }

        if ((rect1.top < rect3.bottom) && (rect1.left > rect3.left) && (rect1.right < rect3.right)) {
            y1 *= -1;
            score1++;
            if (hitsound != null)
                hitsound.start();
            //sound.playWallSound();
        }

        // else
        //   check=1;
        if((rect1.top>=(canvas.getHeight()-80))&&(rect1.left>=rect.left&&rect1.right<=rect.right))
        {
            y1*=-1;
            if(hitsound!=null)
                hitsound.start();
            //sound.playHitSound();
            score++;
            if(score%3==0)
            {
                if(x1<=0)
                    x1-=2;
                if(x1>0)
                    x1+=2;
                if(y1<=0)
                    y1-=2;
                if(y1>0)
                    y1+=2;
            }
            if((score>=10&&score<=15)||(score>=25&&score<=30))
               power=1;
            else
                power=0;
        }
        if(rect1.top>=canvas.getHeight()-60)
        {
            check=1;
            //sound.playOverSound();
            if(oversound!=null)
                oversound.start();

            SharedPreferences sh = getContext().getSharedPreferences("pong", Context.MODE_PRIVATE);
            int a = sh.getInt("highscore", 0);
            if(a<score) {
                SharedPreferences.Editor myEdit = sh.edit();
                myEdit.putInt("highscore", score);
                canvas.drawText("High Score:" +score, canvas.getWidth()/4-10, (int) (canvas.getHeight()*0.24), paint5);
                myEdit.apply();
            }
            else
            {
                canvas.drawText("High Score:" + a, canvas.getWidth()/4-10, (int) (canvas.getHeight()*0.24), paint5);
            }
            canvas.drawText("Game Over !", canvas.getWidth()/4-30, canvas.getHeight()/3, paint2);
            canvas.drawRect(rect5, paint4);
            canvas.drawText("RESTART",rect5.left+35,rect5.bottom-20,paint2);
        }

        if (rect1.left>130&&rect1.left<getWidth()-130)
            rect3.left=rect1.left-150;
        rect3.top = getHeight()/11+40;
        rect3.right = rect3.left + 300;
        rect3.bottom = rect3.top+30;
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
                float y=event.getY();
                if((x>=rect4.left&&x<=rect4.right)&&(y>=rect4.top&&y<=rect4.bottom))
                    start=1;
                if(start==1) {
                    if (power == 0) {
                        if (x > 130 && x < (getWidth() - 130)) {
                            if (x > rect.left && x < rect.right) {//touched
                                rect.left = (int) x - 150;
                                rect.top = getHeight() - 30;
                                rect.right = rect.left + 300;
                                rect.bottom = getHeight();
                                counter++;
                                if (check == 0)
                                    postInvalidate();
                                return true;
                            }
                        }
                    }
                    if (power == 1) {
                        if (x > 180 && x < (getWidth() - 180)) {
                            if (x > rect.left && x < rect.right) {//touched
                                rect.left = (int) x - 200;
                                rect.top = getHeight() - 30;
                                rect.right = rect.left + 400;
                                rect.bottom = getHeight();
                                counter++;
                                if (check == 0)
                                    postInvalidate();
                                return true;
                            }
                        }
                    }
                }
                if((x>=rect5.left&&x<=rect5.right)&&(y>=rect5.top&&y<=rect5.bottom)){
                    oversound.pause();
                    oversound.seekTo(0);
                    check=0;
                    counter=0;
                    power=0;
                    score=0;
                    score1=0;
                    start=0;
                    x1=8;
                    y1=8;
                    postInvalidate();
                }
                return value;
            }

        }
        return value;
    }
}

