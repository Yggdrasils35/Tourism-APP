package com.xiaoke.helloandroid.spotdataproject;

import android.graphics.Path;

public class Spot {
    public static final String TABLIE_NAME = "spots";

    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_CATEGORIES = "Categories";
    public static final String COLUMN_GRADE = "Grade";
    public static final String COLUMN_LONGITUDE = "Longitude";
    public static final String COLUMN_LATITUDE = "Latitude";
    public static final String COLUMN_TIMELENGHT = "TimeLength";
    public static final String COLUMN_OPENTIME = "OpenTime";
    public static final String COLUMN_CLOSETIME = "CloseTime";

    private int ID;
    private String Name;
    private int Categories;
    private double Grade;
    private double Longitude;
    private double Latitude;
    private double TimeLength;
    private double OpenTime;
    private double CloseTime;

    public Spot() {}

    public Spot(int id, String name, int categories, double grade, double longitude,
                double latitude, double timeLength, double openTime, double closeTime) {
        this.ID = id;
        this.Name = name;
        this.Categories = categories;
        this.Grade = grade;
        this.Longitude = longitude;
        this.Latitude = latitude;
        this.TimeLength = timeLength;
        this.OpenTime = openTime;
        this.CloseTime = closeTime;
    }
    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getCategories() {
        return Categories;
    }

    public void setCategories(int categories) {
        Categories = categories;
    }

    public double getGrade() {
        return Grade;
    }

    public void setGrade(double grade) {
        Grade = grade;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getTimeLength() {
        return TimeLength;
    }

    public void setTimeLength(double timeLength) {
        TimeLength = timeLength;
    }

    public double getOpenTime() {
        return OpenTime;
    }

    public void setOpenTime(double openTime) {
        OpenTime = openTime;
    }

    public double getCloseTime() {
        return CloseTime;
    }

    public void setCloseTime(double closeTime) {
        CloseTime = closeTime;
    }

    @Override
    public String toString() {
        String result = "";
        result += "ID：" + this.ID + ",";
        result += "景点名称：" + this.Name + ",";
        result += "景点分类：" + this.Categories + ",";
        result += "经度：" + this.Longitude + ",";
        result += "纬度：" + this.Latitude + ",";
        result += "游玩时间：" + this.TimeLength + ",";
        result += "开门时间：" + this.OpenTime + ",";
        result += "关门时间：" + this.CloseTime + ",";
        return result;
    }
}
