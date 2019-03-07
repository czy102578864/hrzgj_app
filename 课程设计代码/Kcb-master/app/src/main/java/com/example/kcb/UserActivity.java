package com.example.kcb;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {

    TextView text_account;
    TextView text_password;
    TextView text_superpassword;


    //SQLite Helper类
    private DatabaseHelper databaseHelper = new DatabaseHelper
            (this, "User.db", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        //从数据库读取用户数据
        loadUserData();
    }

    //从数据库加载数据
    private void loadUserData() {
        ArrayList<User> usersList = new ArrayList<>(); //用户列表
        SQLiteDatabase sqLiteDatabase =  databaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from User where account = '201605010319' ", null);
        if (cursor.moveToFirst()) {
            do {
                usersList.add(new User(
                        cursor.getString(cursor.getColumnIndex("account")),
                        cursor.getString(cursor.getColumnIndex("password")),
                        cursor.getString(cursor.getColumnIndex("superpassword"))
                ));
            } while(cursor.moveToNext());
        }


        cursor.close();

        //使用从数据库读取出来的课程信息来加载课程表视图
        for (User user : usersList) {
            createUserView(user);
        }
    }

    //创建用户视图
    private void createUserView(final User user) {
        text_account=findViewById(R.id.u_account);
        text_account.setText(user.getAccount());
        text_password=findViewById(R.id.u_passwd);
        text_password.setText(user.getPassword());
        text_superpassword=findViewById(R.id.u_spasswd);
        text_superpassword.setText(user.getSuperpassword());
        }
}


