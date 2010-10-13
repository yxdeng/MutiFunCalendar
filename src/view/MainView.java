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
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;

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
//      foreground.setColor(getResources().getColor(
//            R.color.puzzle_foreground));
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
//        int j = getFirDayWeekOfMonth(now_year, now_month) - 1;
        int j = myCal.getFirDayWeekOfMonth()-1;
//      System.out.println("j:"+j);
//        for (Integer i = 1; i <= getMonthDays(now_year, now_month); i++) {
        for (Integer i = 1; i <= myCal.getMonthDays(); i++) {
//            if (i == now_day) {
            if (i == myCal.getNow_day() && myCal.getCurrent_month()==myCal.getNow_month()) {
                //draw today
//                System.out.println("i:"+now_day);
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
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy'年'MM'月'dd'日'");
//        return sdf.format(now.getTime());
        return myCal.getCurrent_year()+"年"+(myCal.getCurrent_month()+1)+"月"+myCal.getCurrent_day()+"日";
    }

    private void initDate() {
//        this.now_day = now.get(Calendar.DATE);
//        this.now_month = now.get(Calendar.MONTH);
//        this.now_year = now.get(Calendar.YEAR);
        myCal = new NomalCalendar();
        weeks = myCal.getWeeks();
    }

//    private int getFirDayWeekOfMonth(int year, int month) {
//        Calendar cal = Calendar.getInstance();
//        cal.set(year, month, 1);
//        return cal.get(Calendar.DAY_OF_WEEK);
//    }
//
//    private int getMonthDays(int year, int month) {
//        Calendar cal = Calendar.getInstance();
//        cal.set(year, month, 1);
//        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
////        return Calendar.DAY_OF_MONTH;
//    }
    private float width;    // width of one tile
    private float height;   // height of one tile
    private int selX;       // X index of selection
    private int selY;       // Y index of selection
    private final Rect selRect = new Rect();
//    private Calendar now = Calendar.getInstance();
    private String[] weeks ;//= {"日", "一", "二", "三", "四", "五", "六"};
//    private int now_year;
//    private int now_month;
//    private int now_day;

    private NomalCalendar myCal;
}
