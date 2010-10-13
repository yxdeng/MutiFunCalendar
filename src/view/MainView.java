/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.view.View;
import contraler.NomalCalendar;

/**
 *
 * @author yxdeng
 */
public class MainView extends View {

    public MainView(Context context) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
        initDate();        
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //draw the head
        Paint light = new Paint();
        light.setColor(Color.WHITE);
        float oldheight = height;
        float rheight = getHeight() - height;

        Paint head = new Paint();
        head.setColor(Color.GREEN);
        canvas.drawLine(0, oldheight, getWidth(), oldheight, head);
        canvas.drawLine(width, 0, width, oldheight, head);
        canvas.drawLine(getWidth() - width, 0, getWidth() - width, oldheight, head);

        height = rheight / 7f;

        // Draw the minor grid lines
        for (int i = 1; i < 7; i++) {
            canvas.drawLine(0, i * height + oldheight, getWidth(), i * height + oldheight,
                    light);
            canvas.drawLine(i * width, oldheight, i * width, getHeight(),
                    light);
        }

        //draw this month
        Paint montPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        montPaint.setColor(Color.BLUE);
        montPaint.setStyle(Style.FILL);
        montPaint.setTextScaleX(width / height);
        montPaint.setTextAlign(Paint.Align.CENTER);
        montPaint.setTextSize(rheight * 0.5f);
        canvas.drawText(String.valueOf(myCal.getCurrent_month()+1), getWidth() / 2f, (rheight / 2f)+2*oldheight, montPaint);

        Paint foreground = new Paint(Paint.ANTI_ALIAS_FLAG);
        foreground.setColor(Color.RED);
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
        int count = 1;
        int j = myCal.getFirDayWeekOfMonth()-1;
        for (Integer i = 1; i <= myCal.getMonthDays(); i++) {
            if (i == myCal.getNow_day() && myCal.getCurrent_month()==myCal.getNow_month()) {
                Paint today = new Paint();
                today.setColor(Color.YELLOW);
                Rect todayRect = new Rect();
                todayRect.set((int)(j*width), (int)(count * height+ oldheight), (int)(j*width+width), (int)(count * height+height+ oldheight));
                canvas.drawRect(todayRect, today);
            }
            
            canvas.drawText(i.toString(), j
                    * width + x, count * height + y + oldheight, foreground);
            j++;
            if (j == 7) {
                j = 0;
                count++;
            }
        }


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w / 7f;
        height = h / 7f;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private String getDate() {
        return myCal.getCurrent_year()+"年"+(myCal.getCurrent_month()+1)+"月"+myCal.getCurrent_day()+"日";
    }

    private void initDate() {
        myCal = new NomalCalendar();
        weeks = myCal.getWeeks();
    }
    private float width;    // width of one tile
    private float height;   // height of one tile
    private int selX;       // X index of selection
    private int selY;       // Y index of selection
    private final Rect selRect = new Rect();
    private String[] weeks ;
    private NomalCalendar myCal;
}
