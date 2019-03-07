package com.example.kcb;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NoteActivity extends AppCompatActivity {
    private MyDatabaseHelper userHelper;
    private MyDatabaseHelper noteHelper;

    EditText edit_note;
    Button btn_note;
    String account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        edit_note = (EditText)findViewById(R.id.edit_note);
        btn_note = (Button)findViewById(R.id.btn_note);

        userHelper = new MyDatabaseHelper(this,"User.db",null,1);
        SQLiteDatabase user = userHelper.getWritableDatabase();
        Cursor cursor = user.query("User",null, null, null, null, null, null);
        if(cursor.moveToFirst()){
            do {
                String Account = cursor.getString(cursor.getColumnIndex("account"));
                int Active = cursor.getInt(cursor.getColumnIndex("active"));
                if(Active == 1){
                    account = Account;
                }
            }while (cursor.moveToNext());
        }

        noteHelper = new MyDatabaseHelper(this,"User.db",null,1);
        btn_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase note = noteHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                if(edit_note.getText().toString().isEmpty()){
                    Toast.makeText(NoteActivity.this,"笔记为空",Toast.LENGTH_SHORT).show();
                }else {
                    values.put("account",account);
                    values.put("note",edit_note.getText().toString());
                    note.insert("Note",null,values);
                }
                Intent intent = new Intent(NoteActivity.this,ShownoteActivity.class);
                startActivity(intent);
            }
        });
    }
}
