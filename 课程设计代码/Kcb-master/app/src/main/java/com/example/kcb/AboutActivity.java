package com.example.kcb;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AboutActivity extends AppCompatActivity {

    Button userinfo;
    Button note;
    Button daojishi;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setFinishOnTouchOutside(false);

        userinfo = (Button)findViewById(R.id.userinfo);
        userinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });

        note = (Button)findViewById(R.id.note);
        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutActivity.this, ShownoteActivity.class);
                startActivity(intent);
            }
        });

        daojishi = (Button)findViewById(R.id.daojishi);
        daojishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutActivity.this,TimeclockActivity.class);
                startActivity(intent);
            }
        });
    }


}
