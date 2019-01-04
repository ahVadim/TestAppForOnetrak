package com.example.vadim.testappforonetrak.Model;

import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Data {

    @SerializedName("aerobic")
    private int mAerobic;
    @SerializedName("date")
    private long mDate;
    @SerializedName("run")
    private int mRun;
    @SerializedName("walk")
    private int mWalk;


    public String getDate() {
        Date date = new Date(mDate);
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format(mDate);
    }

    public void setDate(long date) {
        mDate = date;
    }

    public String getStringAerobic() {
        return Integer.toString(mAerobic);
    }

    public void setAerobic(int aerobic) {
        mAerobic = aerobic;
    }

    public String getStringRun() {
        return Integer.toString(mRun);
    }

    public void setRun(int run) {
        mRun = run;
    }

    public String getStringWalk() {
        return Integer.toString(mWalk);
    }

    public void setWalk(int walk) {
        mWalk = walk;
    }

    public int getSumSteps(){
        return (mAerobic+mRun+mWalk);
    }

    public int getAerobic() {
        return mAerobic;
    }

    public int getRun() {
        return mRun;
    }

    public int getWalk() {
        return mWalk;
    }
}
