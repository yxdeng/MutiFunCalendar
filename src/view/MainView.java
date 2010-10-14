/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import contraler.NomalCalendar;
import org.me.mutifuncalendar.MainActivity;

/**
 *
 * @author yxdeng
 */
public class MainView extends View {

    public MainView(Context context) {
        super(context);
        setBackgroundResource(org.me.mutifuncalendar.R.drawable.background);

        this.main = (MainActivity) context;
        setFocusable(true);
        setFocusableInTouchMode(true);
        initDate();
        headpaint.setColor(Color.TRANSPARENT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //draw the head
        drawHead(canvas, oldheight);
        // Draw the minor grid lines
        drawGrid(canvas, oldheight);
        //draw this month
        drawMonth(canvas, oldheight, getHeight() - oldheight);
        //draw content
        drawConcent(canvas, oldheight);
        //draw select
        drawSelectRect(canvas);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w / 7f;
        oldheight = h / 7f;
        height = (getHeight() - oldheight) / 7f;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();
            //current_month--
            if (x < width && y < oldheight) {
                this.lefticon = org.me.mutifuncalendar.R.drawable.arrow_left_last;
                if (myCal.getCurrent_month() == 0) {
                    myCal.setCurrent_year((myCal.getCurrent_year() - 1));
                }
                myCal.setCurrent_month((myCal.getCurrent_month() + 11) % 12);
                this.invalidate();
                return true;
            }
            //current_month++
            if (x > 6 * width && y < oldheight) {
                this.righticon = org.me.mutifuncalendar.R.drawable.arrow_right_last;
                if (myCal.getCurrent_month() == 11) {
                    myCal.setCurrent_year((myCal.getCurrent_year() + 1));
                }
                myCal.setCurrent_month((myCal.getCurrent_month() + 1) % 12);
                this.invalidate();
                return true;
            }
            if (y > oldheight) {
                select((int) (event.getX() / width),
                        (int) ((event.getY() - oldheight) / height));
                return true;
            }
            headpaint.setARGB(100, 100, 200, 100);
            this.invalidate();
            this.main.showMonthView(this);
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            headpaint.setColor(Color.TRANSPARENT);
            this.lefticon = org.me.mutifuncalendar.R.drawable.arrow_left_first;
            this.righticon = org.me.mutifuncalendar.R.drawable.arrow_right_first;
            this.invalidate();
        }

        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//      Log.d(TAG, "onKeyDown: keycode=" + keyCode + ", event="
//            + event);
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                myCal.setCurrent_year((myCal.getCurrent_year() - 1));
                this.invalidate();
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                myCal.setCurrent_year((myCal.getCurrent_year() + 1));
                this.invalidate();
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                if (myCal.getCurrent_month() == 0) {
                    myCal.setCurrent_year((myCal.getCurrent_year() - 1));
                }
                myCal.setCurrent_month((myCal.getCurrent_month() + 11) % 12);
                this.invalidate();
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                if (myCal.getCurrent_month() == 11) {
                    myCal.setCurrent_year((myCal.getCurrent_year() + 1));
                }
                myCal.setCurrent_month((myCal.getCurrent_month() + 1) % 12);
                this.invalidate();
                break;
            default:
                return super.onKeyDown(keyCode, event);
        }
        return true;
    }

    private void drawHead(Canvas canvas, float oldheight) {
        Rect headRect = new Rect();
        headRect.set((int)width, 0, (int)(6*width), (int)oldheight);
        canvas.drawRect(headRect, headpaint);
//        canvas.drawLine(0, oldheight, getWidth(), oldheight, head);
//        canvas.drawLine(width, 0, width, oldheight, head);
//        canvas.drawLine(getWidth() - width, 0, getWidth() - width, oldheight, head);

        Rect leftr = new Rect();
        leftr.set(0, 0, (int) width, (int) oldheight);
//        Path p = new Path();
        BitmapDrawable bd = (BitmapDrawable) getResources().getDrawable(lefticon);
//        bd.getBitmap();
        canvas.drawBitmap(bd.getBitmap(), null, leftr, null);
        Rect rightr = new Rect();
        rightr.set((int) width * 6, 0, (int) getWidth(), (int) oldheight);
        bd = (BitmapDrawable) getResources().getDrawable(righticon);
        canvas.drawBitmap(bd.getBitmap(), null, rightr, null);

//        canvas.drawPath(new Path(), head);
    }

    private void drawGrid(Canvas canvas, float oldheight) {
        Paint light = new Paint();
        light.setColor(Color.WHITE);
        for (int i = 1; i < 7; i++) {
            canvas.drawLine(0, i * height + oldheight, getWidth(), i * height + oldheight,
                    light);
            canvas.drawLine(i * width, oldheight, i * width, getHeight(),
                    light);
        }
    }

    private void drawMonth(Canvas canvas, float oldheight, float rheight) {
        Paint montPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        montPaint.setColor(Color.GRAY);
        montPaint.setStyle(Style.FILL);
        montPaint.setTextScaleX(width / height);
        montPaint.setTextAlign(Paint.Align.CENTER);
        montPaint.setTextSize(rheight * 0.5f);
        canvas.drawText(String.valueOf(myCal.getCurrent_month() + 1), getWidth() / 2f, (rheight / 2f) + 2 * oldheight, montPaint);

    }

    private void drawConcent(Canvas canvas, float oldheight) {
        Paint foreground = new Paint(Paint.ANTI_ALIAS_FLAG);
        foreground.setColor(Color.BLACK);
        foreground.setStyle(Style.FILL);
        foreground.setTextScaleX(width / height);
        foreground.setTextAlign(Paint.Align.CENTER);
        foreground.setTextSize(height * 0.5f);
        // Draw the number in the center of the tile
        FontMetrics fm = foreground.getFontMetrics();

        // Centering in X: use alignment (and X at midpoint)
        float x = width / 2;
        // Centering in Y: measure ascent/descent first
        float y = height / 2 - (fm.ascent + fm.descent) / 2;

        //draw the date of today

        canvas.drawText(getDate(), getWidth() / 2f, oldheight / 2f, foreground);

        //draw the week title
        for (int i = 0; i < 7; i++) {
            canvas.drawText(weeks[i], i
                    * width + x, y + oldheight, foreground);
        }

        //draw all the month
        locations = new int[6][7];
        int count = 1;
        int j = myCal.getFirDayWeekOfMonth() - 1;
        for (Integer i = 1; i <= myCal.getMonthDays(); i++) {
            locations[count - 1][j] = i;

            if (i == myCal.getNow_day()
                    && myCal.getCurrent_month() == myCal.getNow_month()
                    && myCal.getCurrent_year() == myCal.getNow_year()) {
                drawToday(canvas,
                        (int) (j * width),
                        (int) (count * height + oldheight),
                        (int) (j * width + width),
                        (int) (count * height + height + oldheight));
            }

            canvas.drawText(i.toString(),
                    j * width + x,
                    count * height + y + oldheight,
                    foreground);
            j++;
            if (j == 7) {
                j = 0;
                count++;
            }
        }

        for (int i = 0; i < 6; i++) {
            for (int m = 0; m < 7; m++) {
                System.out.print(locations[i][m] + " ");
            }
            System.out.println();
        }
    }

    private void drawToday(Canvas canvas, int x0, int y0, int x1, int y1) {
        Paint today = new Paint();
        today.setColor(Color.YELLOW);
        Rect todayRect = new Rect();
        todayRect.set(x0, y0, x1, y1);
        canvas.drawRect(todayRect, today);
    }

    private void drawSelectRect(Canvas canvas) {
        Paint selected = new Paint();
//      selected.setColor(Color.TRANSPARENT);
        selected.setARGB(100, 100, 200, 100);
        canvas.drawRect(selRect, selected);
    }

    private void getRect(int x, int y, Rect rect) {
        rect.set((int) (x * width),
                (int) (y * height + oldheight),
                (int) (x * width + width),
                (int) (y * height + height + oldheight));
    }

    private void select(int x, int y) {
        invalidate(selRect);
        selX = Math.min(Math.max(x, 0), 8);
        selY = Math.min(Math.max(y, 0), 8);
        getRect(selX, selY, selRect);
        invalidate(selRect);
    }

    public int getSelectDay() {
        if (selY <= 0) {
            return 0;
        }
        return locations[selY - 1][selX];
    }

    private String getDate() {
        return myCal.getCurrent_year() + "年" + (myCal.getCurrent_month() + 1) + "月";//+ myCal.getCurrent_day() + "日";
    }

    private void initDate() {
        myCal = new NomalCalendar();
        weeks = myCal.getWeeks();
    }

    public NomalCalendar getMyCalendar() {
        return myCal;
    }
    private float width;    // width of one tile
    private float height;   // height of one tile
    private float oldheight;
    private int selX;       // X index of selection
    private int selY;       // Y index of selection
    private final Rect selRect = new Rect();
    private  Paint headpaint = new Paint();
//    private final Rect headRect = new Rect();
//    private int headColor = Color.TRANSPARENT;
    private String[] weeks;
    private NomalCalendar myCal;
    private int[][] locations;//= new int[6][7];
    private int lefticon = org.me.mutifuncalendar.R.drawable.arrow_left_first;
    private int righticon = org.me.mutifuncalendar.R.drawable.arrow_right_first;
    private MainActivity main;
}
