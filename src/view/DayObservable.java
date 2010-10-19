/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import contraler.NomalCalendar;
import java.util.Observable;

/**
 *
 * @author yxdeng
 */
public class DayObservable extends Observable{

    public DayObservable(NomalCalendar nc) {
        this.nc = nc;
    }

    public void change(){
        this.setChanged();
        this.notifyObservers();
    }

    public NomalCalendar getCalendar(){
        return nc;
    }

    private NomalCalendar nc;
}
