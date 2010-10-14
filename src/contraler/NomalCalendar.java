/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package contraler;

import java.util.Calendar;

/**
 *
 * @author yxdeng
 */
public class NomalCalendar {

    /**
     * use to init the now_Date and current_Date
     */
    public NomalCalendar() {
        cal = Calendar.getInstance();
        this.now_day = cal.get(Calendar.DATE);
        this.now_month = cal.get(Calendar.MONTH);
        this.now_year = cal.get(Calendar.YEAR);
        this.current_day = now_day;
        this.current_month = now_month;
        this.current_year = now_year;
    }

    /**
     * to get the first of the week from current_month
     * @return int
     */
     public int getFirDayWeekOfMonth() {
        cal.set(current_year, current_month, 1);
        int res = cal.get(Calendar.DAY_OF_WEEK);
        cal.set(current_year, current_month, current_day);
        return res;
    }
     public int getDayWeekOfMonth() {
        cal.set(current_year, current_month, current_day);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

     /**
      * return the sum of the current_month
      * @return
      */
     public int getMonthDays() {
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

     /**
      * return the weektitle
      * @return String[]
      */
     public String[] getWeeks(){
         return weeks;
     }

     /**
      * to set the current_Date
      * @param year int
      * @param month int
      * @param day  int
      */
    public void setDate( int year, int month, int day){
        this.current_day = day;
        this.current_month = month;
        this.current_year = year;
    }
    
    public void setCurrent_day(int current_day) {
        this.current_day = current_day;
    }

    public void setCurrent_month(int current_month) {
        this.current_month = current_month;
    }

    public void setCurrent_year(int current_year) {
        this.current_year = current_year;
    }


    public int getCurrent_day(){
        return current_day;
    }

    public int getCurrent_month() {
        return current_month;
    }

    public int getCurrent_year() {
        return current_year;
    }

    public int getNow_day() {
        return now_day;
    }

    public int getNow_month() {
        return now_month;
    }

    public int getNow_year() {
        return now_year;
    }

    @Override
    public String toString() {

        return this.current_day + " "+this.current_month +" "+ this.current_year;
    }


    private Calendar cal ;
    private int now_day;
    private int now_month;
    private int now_year;
    private int current_day;
    private int current_month;
    private int current_year;
    private String[] weeks = {"日", "一", "二", "三", "四", "五", "六"};
}
