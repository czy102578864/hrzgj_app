package com.example.kcb;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class CourseDetailActivity extends AppCompatActivity {

    TextView text_cname;
    TextView text_cteacher;
    TextView text_croom;
    TextView text_cday;
    TextView text_cstart;
    TextView text_cend;

    //SQLite Helper类
    private DatabaseHelper databaseHelper = new DatabaseHelper
            (this, "database.db", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        System.out.println("onCreate");

        text_cname = findViewById(R.id.c_name);
        text_cteacher =findViewById(R.id.c_teacher);
        text_cday = findViewById(R.id.c_day);
        text_croom = findViewById(R.id.c_room);
        text_cstart = findViewById(R.id.c_cstart);
        text_cend = findViewById(R.id.c_cend);
        //从数据库读取用户数据
        loadData();
    }

    private void loadData() {
        ArrayList<Course> coursesList = new ArrayList<>(); //课程列表
        SQLiteDatabase sqLiteDatabase =  databaseHelper.getWritableDatabase();
        Intent intent = getIntent();
        String data = intent.getStringExtra("coursename");
        Cursor cursor = sqLiteDatabase.rawQuery("select * from courses where course_name = ? ",new String[]{data} );
        if (cursor.moveToFirst()) {
            do {
                coursesList.add(new Course(
                        cursor.getString(cursor.getColumnIndex("course_name")),
                        cursor.getString(cursor.getColumnIndex("teacher")),
                        cursor.getString(cursor.getColumnIndex("class_room")),
                        cursor.getInt(cursor.getColumnIndex("day")),
                        cursor.getInt(cursor.getColumnIndex("class_start")),
                        cursor.getInt(cursor.getColumnIndex("class_end"))));
            } while(cursor.moveToNext());
        }
        cursor.close();

        //使用从数据库读取出来的课程信息来加载课程表视图
        for (Course course : coursesList) {
            createCourseView(course);
        }
    }

    //创建课程视图
    public void createCourseView(final Course course){
        System.out.println("createCourseView");
        text_cname.setText(course.getCourseName());
        text_cteacher.setText(course.getTeacher());
        text_croom.setText(course.getClassRoom());
        text_cday.setText(course.getDay()+"");
        text_cstart.setText(course.getStart()+"");
        text_cend.setText(course.getEnd()+"");
    }
}
