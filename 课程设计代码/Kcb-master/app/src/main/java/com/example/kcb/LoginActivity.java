package com.example.kcb;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText text_account;
    EditText text_password;
    Button button_login;
    TextView text_forget;
    TextView text_sign;
    ImageView image_background;

    boolean jump = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        text_account = (EditText)findViewById(R.id.text_account);
        text_password = (EditText)findViewById(R.id.text_password);
        button_login = (Button)findViewById(R.id.button_login);
        text_sign = (TextView)findViewById(R.id.text_sign);
        text_forget = (TextView)findViewById(R.id.text_forget);
        image_background = (ImageView)findViewById(R.id.image_background);
        image_background.setImageResource(R.drawable.face);

        text_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignActivity.class);
                startActivity(intent);
            }
        });
        text_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgetActivity.class);
                startActivity(intent);
            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initUser();
                if(jump) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(LoginActivity.this, "输入错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initUser(){
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this, "User.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        Cursor cursor = db.query("User", null, null, null, null, null, null, null);
        if(cursor.moveToFirst()){
            do{
                String Account = cursor.getString(cursor.getColumnIndex("account"));
                String Password = cursor.getString(cursor.getColumnIndex("password"));
                Log.d("loginActivity", Account);
                Log.d("loginActivity", Password);
                values.put("active", "0");
                db.update("User", values, "account = ?", new String[] {Account});
                if (Account.equals(text_account.getText().toString())  && Password.equals(text_password.getText().toString()) ){
                    jump = true;
                    values.put("active", "1");
                    db.update("User", values, "account = ?", new String[] {text_account.getText().toString()});
                }


            }while(cursor.moveToNext());
        }
        cursor.close();
    }
}
