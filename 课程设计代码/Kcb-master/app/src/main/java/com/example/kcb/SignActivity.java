package com.example.kcb;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignActivity extends AppCompatActivity {

    EditText edit_account;
    EditText edit_password;
    EditText edit_superpassword;
    EditText edit_surepassword;
    TextView text_major;

    Button btn_sign;

    boolean success = true;

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        dbHelper = new MyDatabaseHelper(this, "User.db", null, 1);

        edit_account = (EditText)findViewById(R.id.edit_account);
        edit_password = (EditText)findViewById(R.id.edit_password);
        edit_surepassword = (EditText)findViewById(R.id.edit_surepassword);
        edit_superpassword = (EditText)findViewById(R.id.edit_superpassword);
        btn_sign = (Button)findViewById(R.id.btn_sign);

        /*text_major.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignActivity.this, MajorActivity.class);
                startActivityForResult(intent, 1);

            }
        });*/

        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Account = edit_account.getText().toString();
                String Password = edit_password.getText().toString();
                String superPassword = edit_superpassword.getText().toString();
               // String Major = text_major.getText().toString();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                Cursor cursor = db.query("User", null, null, null, null, null, null, null);
                if(cursor.moveToFirst()){
                    do{

                        String user = cursor.getString(cursor.getColumnIndex("account"));
                        String supass = cursor.getString(cursor.getColumnIndex("superpassword"));
                        if (user.equals(Account) || supass.equals(superPassword)){
                            success = false;
                        }
                    }while(cursor.moveToNext());
                }
                cursor.close();//检测用户是否已存在
                if(Account.isEmpty()||Password.isEmpty() || superPassword.isEmpty() ){
                    Toast.makeText(SignActivity.this,"帐号、密码不能为空!",Toast.LENGTH_SHORT).show();
                }else if (Password.equals(edit_surepassword.getText().toString())){//确认前后密码是否一致
                    if (success) {
                        values.put("account", Account);
                        values.put("password", Password);
                        values.put("superpassword", superPassword);
                        //values.put("major", Major);
                        values.put("active", "0");
                        db.insert("User", null, values);
                        Intent intent = new Intent(SignActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(SignActivity.this, "用户已存在或超级密码已存在",Toast.LENGTH_SHORT).show();
                        success = true;
                    }

                }else {
                    Toast.makeText(SignActivity.this,"前后密码不一致!",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
