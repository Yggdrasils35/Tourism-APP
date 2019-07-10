package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.database.Cursor;
import android.
public class MainActivity extends AppCompatActivity {

    private spotDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper=new spotDBHelper(this,"SpotInfo.db",null,1);
        Button createDatabase=(Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dbHelper.getWritableDatabase();
            }
        });

        Button queryButton=(Button)  findViewById(R.id.query_data);
        //暂时还不会写查询接口，用这样子cursor慢慢查效率是很低的，后面给出具体的查询要求我们再来写了。。。
        queryButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                Cursor cursor=db.query("SpotExper",null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        int id=cursor.getInt(cursor.getColumnIndex("ID"));
                        String name=cursor.getString(cursor.getColumnIndex("Name"));
                        double nature=cursor.getDouble(cursor.getColumnIndex("Nature"));
                        double exhibition=cursor.getDouble(cursor.getColumnIndex("Exhibition"));
                        double entertain=cursor.getDouble(cursor.getColumnIndex("Entertain"));
                        double delfood=cursor.getDouble(cursor.getColumnIndex("DelFood"));
                        double humanity=cursor.getDouble(cursor.getColumnIndex("Humanity"));
                        double wanghong=cursor.getDouble(cursor.getColumnIndex("WangHong"));
                        double shopping=cursor.getDouble(cursor.getColumnIndex("Shopping"));
                        double transport=cursor.getDouble(cursor.getColumnIndex("Transport"));
                        double snacklevel=cursor.getDouble(cursor.getColumnIndex("SnackLevel"));
                        int consume=cursor.getInt(cursor.getColumnIndex("Consume"));
                        int visitornum=cursor.getInt(cursor.getColumnIndex("VisitorNum"));
                    }while(cursor.moveToNext());
                }
                cursor.close();
            }
        });
    }


}
