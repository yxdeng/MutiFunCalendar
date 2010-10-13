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
import android.view.MotionEvent;
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
        drawHead(canvas, oldheight);
        // Draw the minor grid lines
        drawGrid(canvas, oldheight);
        //draw this month
        drawMonth(canvas, oldheight, getHeight() - oldheight);
        //draw content
        drawConcent(canvas, oldheight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w / 7f;
        oldheight = h / 7f;
        height = (getHeight() - oldheight)/7f;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN) {
            return super.onTouchEvent(event);
        }

        float x = event.getX();
        float y = event.getY();
        //current_month--
        if(x<width && y < oldheight ){
            if(myCal.getCurrent_month()==0)
                myCal.setCurrent_year((myCal.getCurrent_year()-1));
            myCal.setCurrent_month((myCal.getCurrent_month()+11)%12);
            this.invalidate();
            return true;
        }
        //current_month++
        if(x>6*width && y < oldheight){
            if(myCal.getCurrent_month()==11)
                myCal.setCurrent_year((myCal.getCurrent_year()+1));
            myCal.setCurrent_month((myCal.getCurrent_month()+1)%12);
            this.invalidate();
            return true;
        }
        if( y > oldheight)
        select((int) (event.getX() / width),
                (int) ((event.getY()-oldheight) / height));
//        game.showKeypadOrError(selX, selY);
//        Log.d(TAG, "onTouchEvent: x " + selX + ", y " + selY);
        return true;
    }

    private void drawHead(Canvas canvas, float oldheight) {
        Paint head = new Paint();
        head.setColor(Color.GREEN);
        canvas.drawLine(0, oldheight, getWidth(), oldheight, head);
        canvas.drawLine(width, 0, width, oldheight, head);
        canvas.drawLine(getWidth() - width, 0, getWidth() - width, oldheight, head);
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
        montPaint.setColor(Color.BLUE);
        montPaint.setStyle(Style.FILL);
        montPaint.setTextScaleX(width / height);
        montPaint.setTextAlign(Paint.Align.CENTER);
        montPaint.setTextSize(rheight * 0.5f);
        canvas.drawText(String.valueOf(myCal.getCurrent_month() + 1), getWidth() / 2f, (rheight / 2f) + 2 * oldheight, montPaint);

    }

    private void drawConcent(Canvas canvas, float oldheight) {
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
        int j = myCal.getFirDayWeekOfMonth() - 1;
        for (Integer i = 1; i <= myCal.getMonthDays(); i++) {
            if (i == myCal.getNow_day()
                    && myCal.getCurrent_month() == myCal.getNow_month()
                    &&myCal.getCurrent_year()==myCal.getNow_year()) {
                drawToday(canvas,
                        (int) (j * width),
                        (int) (count * height + oldheight),
                        (int) (j * width + width),
                        (int) (count * height + height + oldheight));
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

    private void drawToday(Canvas canvas, int x0, int y0, int x1, int y1) {
        Paint today = new Paint();
        today.setColor(Color.YELLOW);
        Rect todayRect = new Rect();
        todayRect.set(x0, y0, x1, y1);
        canvas.drawRect(todayRect, today);
    }

    private void getRect(int x, int y, Rect rect) {
        rect.set((int) (x * width),
                (int) (y * height + oldheight),
                (int) (x * width + width), (int) (y * height + height + oldheight));
    }

    private void select(int x, int y) {
      invalidate(selRect);
      selX = Math.min(Math.max(x, 0), 8);
      selY = Math.min(Math.max(y, 0), 8);
      getRect(selX, selY, selRect);
      invalidate(selRect);
   }

    private String getDate() {
        return myCal.getCurrent_year() + "年" + (myCal.getCurrent_month() + 1) + "月" ;//+ myCal.getCurrent_day() + "日";
    }

    private void initDate() {
        myCal = new NomalCalendar();
        weeks = myCal.getWeeks();
    }
    private float width;    // width of one tile
    private float height;   // height of one tile
    private float oldheight;
    private int selX;       // X index of selection
    private int selY;       // Y index of selection
    private final Rect selRect = new Rect();
    private String[] weeks;
    private NomalCalendar myCal;
}
