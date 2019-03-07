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
import android.widget.Toast;

public class ForgetActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;

    Button btn_check;
    EditText edit_super;
    EditText edit_newpassword;
    EditText edit_checkpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        dbHelper = new MyDatabaseHelper(this, "User.db", null, 1);

        edit_super = (EditText)findViewById(R.id.edit_super);
        edit_newpassword = (EditText)findViewById(R.id.edit_newpassword);
        edit_checkpassword = (EditText)findViewById(R.id.edit_checkpassword);
        btn_check = (Button)findViewById(R.id.btn_check);
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                Cursor cursor = db.query("User", null, null, null, null, null, null, null);
                if(cursor.moveToFirst()){
                    do{

                        String superPasword = cursor.getString(cursor.getColumnIndex("superpassword"));

                        if (superPasword.equals(edit_super.getText().toString())){
                            if (edit_newpassword.getText().toString().isEmpty() || edit_checkpassword.getText().toString().isEmpty()){
                                Toast.makeText(ForgetActivity.this, "新密码不能为空！", Toast.LENGTH_SHORT).show();
                            }else if (edit_newpassword.getText().toString().equals(edit_checkpassword.getText().toString())) {
                                values.put("password", edit_newpassword.getText().toString());
                                db.update("User", values, "superpassword = ?", new String[]{superPasword});
                                Toast.makeText(ForgetActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ForgetActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(ForgetActivity.this, "前后密码不一致！", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }while(cursor.moveToNext());
                }
                cursor.close();
            }
        });

    }
}
