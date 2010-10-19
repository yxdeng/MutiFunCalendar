/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;
import contraler.NomalCalendar;
import org.me.mutifuncalendar.CalendarActivity;

/**
 *
 * @author yxdeng
 */
public class DayView extends View {

    public DayView(Context context, NomalCalendar myCal) {
        super(context);
        this.setId(83);
        ma = (CalendarActivity)context;
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.myCal = myCal;
        weeks = myCal.getWeeks();
        setBackgroundResource(org.me.mutifuncalendar.R.drawable.background);        
        headpaint.setColor(Color.TRANSPARENT);
    }

      @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable p = super.onSaveInstanceState();

        Bundle bundle = new Bundle();
        bundle.putInt("currentday", myCal.getCurrent_day());
        bundle.putInt("currentmonth", myCal.getCurrent_month());
        bundle.putInt("currentyear", myCal.getCurrent_year());
        bundle.putInt("selectday", myCal.getCurrent_day());
        bundle.putParcelable("monthview", p);
        System.out.println(bundle.getInt("currentyear")+" "+bundle.getInt("currentmonth")+" "+ bundle.getInt("currentday"));
        return bundle;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();
            //current_month--
            if (x < width && y < oldheight) {
                this.lefticon = org.me.mutifuncalendar.R.drawable.arrow_left_last;
                if (myCal.getCurrent_day() == 1) {
                    if (myCal.getCurrent_month() == 0) {
                        myCal.setCurrent_year((myCal.getCurrent_year() - 1));
                    }
                    myCal.setCurrent_month((myCal.getCurrent_month() + 11) % 12);
                    myCal.setCurrent_day(myCal.getMonthDays());
                }else
                    myCal.setCurrent_day(myCal.getCurrent_day() - 1);
                this.invalidate();
                return true;
            }
            //current_month++
            if (x > 6 * width && y < oldheight) {
                this.righticon = org.me.mutifuncalendar.R.drawable.arrow_right_last;
                if (myCal.getCurrent_day() == myCal.getMonthDays()) {
                    if (myCal.getCurrent_month() == 11) {
                        myCal.setCurrent_year((myCal.getCurrent_year() + 1));
                    }
                    myCal.setCurrent_month((myCal.getCurrent_month() + 1) % 12);
                    myCal.setCurrent_day(1);
                }else
                    myCal.setCurrent_day(myCal.getCurrent_day()+1);
                this.invalidate();
                return true;
            }
            if(y>(getHeight()-height)&&x>5*width){
//                this.ma.setContentView(this.main);
                DayObservable dob = new DayObservable(myCal);
                dob.addObserver(ma);
                dob.change();
            }
            if (y > oldheight) {
//                select((int) (event.getX() / width),
//                        (int) ((event.getY() - oldheight) / height));
////                sleep(500);
//                this.main.setContentView(new DayView(main,this));
                return true;
            }
            headpaint.setARGB(100, 100, 200, 100);
            this.invalidate();
            this.ma.showDayView(this);
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            headpaint.setColor(Color.TRANSPARENT);
            this.lefticon = org.me.mutifuncalendar.R.drawable.arrow_left_first;
            this.righticon = org.me.mutifuncalendar.R.drawable.arrow_right_first;
            this.invalidate();
        }
        return super.onTouchEvent(event);
//        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //draw the head
        drawHead(canvas);
        // Draw the minor grid lines
//        drawGrid(canvas);
        //draw the currentday
        drawCurrentDay(canvas, getHeight() - oldheight);
        //draw content
        drawConcent(canvas);
        //draw select
//        drawSelectRect(canvas);

    }

    private void drawHead(Canvas canvas) {
        //draw the title
        Rect headRect = new Rect();
        headRect.set((int) width, 0, (int) (6 * width), (int) oldheight);
        canvas.drawRect(headRect, headpaint);
        //draw the left icon
        Rect leftr = new Rect();
        leftr.set(0, 0, (int) width, (int) oldheight);
        BitmapDrawable bd = (BitmapDrawable) getResources().getDrawable(lefticon);
        canvas.drawBitmap(bd.getBitmap(), null, leftr, null);
        //draw the right icon
        Rect rightr = new Rect();
        rightr.set((int) width * 6, 0, (int) getWidth(), (int) oldheight);
        bd = (BitmapDrawable) getResources().getDrawable(righticon);
        canvas.drawBitmap(bd.getBitmap(), null, rightr, null);
    }

    private void drawCurrentDay(Canvas canvas, float rheight) {
        Paint montPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        montPaint.setColor(Color.BLACK);
        montPaint.setStyle(Style.FILL);
        montPaint.setTextScaleX(width / height);
        montPaint.setTextAlign(Paint.Align.CENTER);
        montPaint.setTextSize(rheight * 0.5f);
        canvas.drawText(String.valueOf(myCal.getCurrent_day()), getWidth() / 2f, (rheight / 2f) + oldheight, montPaint);

    }

    private void drawGrid(Canvas canvas) {
        Paint light = new Paint();
        light.setColor(Color.WHITE);
        for (int i = 1; i < 7; i++) {
            canvas.drawLine(0, i * height + oldheight, getWidth(), i * height + oldheight,
                    light);
            canvas.drawLine(i * width, oldheight, i * width, getHeight(),
                    light);
        }
    }
    private void drawConcent(Canvas canvas) {
        Paint foreground = new Paint(Paint.ANTI_ALIAS_FLAG);
        foreground.setColor(Color.BLACK);
        foreground.setStyle(Style.FILL);
        foreground.setTextScaleX(width / height);
        foreground.setTextAlign(Paint.Align.CENTER);
        foreground.setTextSize(height * 0.5f);
        //draw the date of current
        canvas.drawText(getDate(), getWidth() / 2f, oldheight / 2f, foreground);
//        System.out.println(myCal);
        canvas.drawText("星期"+weeks[myCal.getDayWeekOfMonth()-1], getWidth() / 2f, oldheight / 2f+oldheight, foreground);
        canvas.drawText("记事", width, getHeight() -height / 4f, foreground);
        canvas.drawText("返回", getWidth()-width, getHeight() -height / 4f, foreground);
    }

    private void drawButtom(Canvas canvas){
        
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w / 7f;
        oldheight = h / 7f;
        height = (getHeight() - oldheight) / 7f;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private String getDate() {
        return myCal.getCurrent_year() + "年" + (myCal.getCurrent_month() + 1) + "月" + myCal.getCurrent_day() + "日";
    }
    public NomalCalendar getMyCalendar() {
        return myCal;
    }
    private float width;    // width of one tile
    private float height;   // height of one tile
    private float oldheight;
    private Paint headpaint = new Paint();
    private String[] weeks;
    private NomalCalendar myCal;
    private int lefticon = org.me.mutifuncalendar.R.drawable.arrow_left_first;
    private int righticon = org.me.mutifuncalendar.R.drawable.arrow_right_first;
    private CalendarActivity ma;
}
