package com.example.kcb;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShownoteActivity extends AppCompatActivity {

    TextView txt_note;

    Button btn_jinote;
    Button btn_bnote;

    String account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_note);
        txt_note = (TextView)findViewById(R.id.txt_note);
        btn_jinote = (Button)findViewById(R.id.btn_jinote);
        btn_bnote = (Button)findViewById(R.id.btn_bnote);

        initnote();

        btn_jinote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShownoteActivity.this,NoteActivity.class);
                startActivity(intent);
            }
        });

        btn_bnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShownoteActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initnote(){
        MyDatabaseHelper userHelper = new MyDatabaseHelper(this,"User.db",null,1);
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
        cursor.close();

        MyDatabaseHelper noteHelper = new MyDatabaseHelper(this,"User.db",null,1);
        SQLiteDatabase note = noteHelper.getWritableDatabase();
        Cursor cursor1 = note.query("Note",null, null, null, null, null, null);
        if(cursor1.moveToFirst()){
            do {
                String Act = cursor1.getString(cursor1.getColumnIndex("account"));
                String note1 = cursor1.getString(cursor1.getColumnIndex("note"));
                if(Act.equals(account)){
                    txt_note.setText(note1);
                }
            }while (cursor1.moveToNext());
        }
        cursor1.close();
    }
}
