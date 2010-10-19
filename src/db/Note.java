/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.io.Serializable;

/**
 *
 * @author yxdeng
 */
public class Note  implements Serializable{

    public Note() {
    }

    public Note(String beginTime, String endTime, String remindTime, String subject, String place, String content, int repeatType, String extraInfo) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.remindTime = remindTime;
        this.subject = subject;
        this.place = place;
        this.content = content;
        this.repeatType = repeatType;
        this.extraInfo = extraInfo;
    }


    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(String remindTime) {
        this.remindTime = remindTime;
    }

    public int getRepeatType() {
        return repeatType;
    }

    public void setRepeatType(int repeatType) {
        this.repeatType = repeatType;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


    private int noteId;
    private String beginTime;
    private String endTime;
    private String remindTime;
    private String subject;
    private String place;
    private String content;
    private int repeatType;
    private String extraInfo;
}
