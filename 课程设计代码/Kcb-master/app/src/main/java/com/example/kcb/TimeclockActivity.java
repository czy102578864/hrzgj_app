package com.example.kcb;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class TimeclockActivity extends AppCompatActivity {
    RecyclerView rv_daojishi;

    int check = 0;
    String account;

    private List<Coursee>courseList = new ArrayList<>();

    Button btn_add;
    Button btn_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeclock);

        timeinit();

        rv_daojishi =(RecyclerView)findViewById(R.id.rv_daojishi);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_daojishi.setLayoutManager(layoutManager);
        CourseAdapter adapter = new CourseAdapter(courseList);
        rv_daojishi.setAdapter(adapter);

        btn_add = (Button)findViewById(R.id.btn_add);
        btn_return = (Button)findViewById(R.id.btn_return);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TimeclockActivity.this,DaojishiActivity.class);
                startActivity(intent);
            }
        });
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TimeclockActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void timeinit(){
        MyDatabaseHelper userHelper = new MyDatabaseHelper(this, "User.db", null, 1);
        SQLiteDatabase user = userHelper.getWritableDatabase();
        Cursor cursor1 = user.query("User", null, null, null, null, null, null);
        if(cursor1.moveToFirst()){
            do {
                String Account = cursor1.getString(cursor1.getColumnIndex("account"));
                account = Account;
            }while (cursor1.moveToNext());
        }
        cursor1.close();

        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this, "Time.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor = db.query("Time", null, null, null,null, null, null);
        if(cursor.moveToFirst()){
            do {
                String textAccount = cursor.getString(cursor.getColumnIndex("text_account"));
                String major = cursor.getString(cursor.getColumnIndex("text_major"));
                String name = cursor.getString(cursor.getColumnIndex("text_sub"));
                String data = cursor.getString(cursor.getColumnIndex("text_data"));

                if (textAccount.equals(account)) {

                    if (data.isEmpty()) {

                    } else {
                        Coursee sub = new Coursee(name, data);
                        courseList.add(sub);
                    }

                }
            }while(cursor.moveToNext());
            cursor.close();
        }
    }
}
