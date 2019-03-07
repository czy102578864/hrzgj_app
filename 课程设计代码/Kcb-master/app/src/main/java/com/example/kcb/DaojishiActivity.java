package com.example.kcb;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DaojishiActivity extends AppCompatActivity{

    TextView text1;
    TextView text22;
    TextView text3;
    TextView text4;
    TextView text5;
    TextView text6;
    TextView text7;

    TextView text_data1;
    TextView text_data2;
    TextView text_data3;
    TextView text_data4;
    TextView text_data5;
    TextView text_data6;
    TextView text_data7;

    Button btn_ok;
    Button btn_back;

    private MyDatabaseHelper dbHelper;
    private MyDatabaseHelper userHelper;

    String account;

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent intent) {
        switch (requestCode){

            case 1:{
                if(requestCode == RESULT_OK){
                    String riqi = intent.getStringExtra("ri_qi");
                    text_data1.setText(riqi);
                }
            }
            break;

            case 2:{
                if(requestCode == RESULT_OK){
                    String riqi = intent.getStringExtra("ri_qi");
                    text_data2.setText(riqi);
                }
            }
            break;

            case 3:{
                if(requestCode == RESULT_OK){
                    String riqi = intent.getStringExtra("ri_qi");
                    text_data3.setText(riqi);
                }
            }
            break;

            case 4:{
                if(requestCode == RESULT_OK){
                    String riqi = intent.getStringExtra("ri_qi");
                    text_data4.setText(riqi);
                }
            }
            break;

            case 5:{
                if(requestCode == RESULT_OK){
                    String riqi = intent.getStringExtra("ri_qi");
                    text_data5.setText(riqi);
                }
            }
            break;

            case 6:{
                if(requestCode == RESULT_OK){
                    String riqi = intent.getStringExtra("ri_qi");
                    text_data6.setText(riqi);
                }
            }
            break;

            case 7:{
                if(requestCode == RESULT_OK){
                    String riqi = intent.getStringExtra("ri_qi");
                    text_data7.setText(riqi);
                }
            }
            break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daojishi);
        text1 = (TextView)findViewById(R.id.text1);
        text22 = (TextView)findViewById(R.id.text22);
        text3 = (TextView)findViewById(R.id.text3);
        text4 = (TextView)findViewById(R.id.text4);
        text5 = (TextView)findViewById(R.id.text5);
        text6 = (TextView)findViewById(R.id.text6);
        text7 = (TextView)findViewById(R.id.text7);
        text_data1 = (TextView)findViewById(R.id.text_data1);
        text_data2 = (TextView)findViewById(R.id.text_data2);
        text_data3 = (TextView)findViewById(R.id.text_data3);
        text_data4 = (TextView)findViewById(R.id.text_data4);
        text_data5 = (TextView)findViewById(R.id.text_data5);
        text_data6 = (TextView)findViewById(R.id.text_data6);
        text_data7 = (TextView)findViewById(R.id.text_data7);

        dbHelper = new MyDatabaseHelper(this, "Time.db", null, 1);
        userHelper = new MyDatabaseHelper(this, "User.db", null, 1);

        btn_ok = (Button)findViewById(R.id.btn_ok);
        btn_back = (Button)findViewById(R.id.btn_back);

        text_data1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DaojishiActivity.this, DateActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        text_data2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DaojishiActivity.this, DateActivity.class);
                startActivityForResult(intent, 2);
            }
        });

        text_data3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DaojishiActivity.this, DateActivity.class);
                startActivityForResult(intent, 3);
            }
        });

        text_data4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DaojishiActivity.this, DateActivity.class);
                startActivityForResult(intent, 4);
            }
        });

        text_data5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DaojishiActivity.this, DateActivity.class);
                startActivityForResult(intent, 5);
            }
        });

        text_data6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DaojishiActivity.this, DateActivity.class);
                startActivityForResult(intent, 6);
            }
        });

        text_data7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DaojishiActivity.this, DateActivity.class);
                startActivityForResult(intent, 7);
            }
        });
        SQLiteDatabase user = userHelper.getWritableDatabase();
        Cursor cursor = user.query("User", null, null, null, null, null, null);
        if(cursor.moveToFirst()){
            do {
                String Account = cursor.getString(cursor.getColumnIndex("account"));
                account = Account;
                text1.setText("离散数学");
                text22.setText("高等数学");
                text3.setText("微机原理");
                text4.setText("操作系统");
                text5.setText("大学英语（三）");
                text6.setText("线性代数");
                text7.setText("c语言");
            }while (cursor.moveToNext());
        }
        cursor.close();

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();

                if(text_data1.getText().toString().isEmpty()){
                    values.put("text_sub", text1.getText().toString());
                    values.put("text_data", text_data1.getText().toString());
                    values.put("text_major", "计算机");
                    values.put("text_account", account);
                    db.insert("Time", null, values);
                    values.clear();
                }else {
                    db.delete("Time", "text_sub = ?", new String[] {text1.getText().toString()});
                    values.put("text_sub", text1.getText().toString());
                    values.put("text_data", text_data1.getText().toString());
                    values.put("text_major", "计算机");
                    values.put("text_account", account);
                    db.insert("Time", null, values);
                }

                if (text_data2.getText().toString().isEmpty()){
                    values.put("text_sub", text22.getText().toString());
                    values.put("text_data", text_data2.getText().toString());
                    values.put("text_major", "计算机");
                    values.put("text_account", account);
                    db.insert("Time", null, values);
                    values.clear();
                }else {
                    db.delete("Time", "text_sub = ?", new String[] {text22.getText().toString()});
                    values.put("text_sub", text22.getText().toString());
                    values.put("text_data", text_data2.getText().toString());
                    values.put("text_major", "计算机");
                    values.put("text_account", account);
                    db.insert("Time", null, values);
                }
                if (text_data3.getText().toString().isEmpty()){
                    values.put("text_sub", text3.getText().toString());
                    values.put("text_data", text_data3.getText().toString());
                    values.put("text_major", "计算机");
                    values.put("text_account", account);
                    db.insert("Time", null, values);
                    values.clear();
                }else {
                    db.delete("Time", "text_sub = ?", new String[] {text3.getText().toString()});
                    values.put("text_sub", text3.getText().toString());
                    values.put("text_data", text_data3.getText().toString());
                    values.put("text_major", "计算机");
                    values.put("text_account", account);
                    db.insert("Time", null, values);
                }
                if (text_data4.getText().toString().isEmpty()){
                    values.put("text_sub", text4.getText().toString());
                    values.put("text_data", text_data4.getText().toString());
                    values.put("text_major", "计算机");
                    values.put("text_account", account);
                    db.insert("Time", null, values);
                    values.clear();
                }else {
                    db.delete("Time", "text_sub = ?", new String[] {text4.getText().toString()});
                    values.put("text_sub", text4.getText().toString());
                    values.put("text_data", text_data4.getText().toString());
                    values.put("text_major", "计算机");
                    values.put("text_account", account);
                    db.insert("Time", null, values);
                }
                if (text_data5.getText().toString().isEmpty()){
                    values.put("text_sub", text5.getText().toString());
                    values.put("text_data", text_data5.getText().toString());
                    values.put("text_major", "计算机");
                    values.put("text_account", account);
                    db.insert("Time", null, values);
                    values.clear();
                }else {
                    db.delete("Time", "text_sub = ?", new String[] {text5.getText().toString()});
                    values.put("text_sub", text5.getText().toString());
                    values.put("text_data", text_data5.getText().toString());
                    values.put("text_major", "计算机");
                    values.put("text_account", account);
                    db.insert("Time", null, values);
                }
                if (text_data6.getText().toString().isEmpty()){
                    values.put("text_sub", text6.getText().toString());
                    values.put("text_data", text_data6.getText().toString());
                    values.put("text_major", "计算机");
                    values.put("text_account", account);
                    db.insert("Time", null, values);
                    values.clear();
                }else {
                    db.delete("Time", "text_sub = ?", new String[] {text6.getText().toString()});
                    values.put("text_sub", text6.getText().toString());
                    values.put("text_data", text_data6.getText().toString());
                    values.put("text_major", "计算机");
                    values.put("text_account", account);
                    db.insert("Time", null, values);
                }
                if (text_data7.getText().toString().isEmpty()){
                    values.put("text_sub", text7.getText().toString());
                    values.put("text_data", text_data7.getText().toString());
                    values.put("text_major", "计算机");
                    values.put("text_account", account);
                    db.insert("Time", null, values);
                    values.clear();
                }else {
                    db.delete("Time", "text_sub = ?", new String[] {text7.getText().toString()});
                    values.put("text_sub", text7.getText().toString());
                    values.put("text_data", text_data7.getText().toString());
                    values.put("text_major", "计算机");
                    values.put("text_account", account);
                    db.insert("Time", null, values);
                }

                if(text_data1.getText().toString().isEmpty() && text_data2.getText().toString().isEmpty() && text_data3.getText().toString().isEmpty() &&
                        text_data4.getText().toString().isEmpty() && text_data5.getText().toString().isEmpty() && text_data6.getText().toString().isEmpty() &&
                        text_data7.getText().toString().isEmpty()){
                    Toast.makeText(DaojishiActivity.this,"日期不能为空!",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(DaojishiActivity.this,TimeclockActivity.class);
                    startActivity(intent);
                }
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DaojishiActivity.this,TimeclockActivity.class);
                startActivity(intent);
            }
        });
    }
}

