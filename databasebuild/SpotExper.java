package com.example.helloworld;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class SpotExper {
    public int ID;                     //景点编号
    public String Name;                //景点名称
    public double Nature;              //自然景观分类
    public double Exhibition;          //展馆分类
    public double Entertain;           //娱乐项目分类
    public double DelFood;             //美食类分类
    public double Humanity;            //人文景点分类
    public double WangHong;            //网红类景点分类
    public double Shopping;            //购物类景点
    public double Transport;           //交通便利程度
    public double SnackLevel;          //美食水平
    public int Consume;                //平均消费
    public int VisitorNum;             //人流量
}