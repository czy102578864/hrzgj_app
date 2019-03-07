package com.example.kcb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper{
    public static final String CREATE_USER = "create table User ("
            + "id integer primary key autoincrement, "
            + "account text, "
            + "password text,"
            + "superpassword text, "
            + "active integer)";
    public static final String CREATE_TIME = "create table Time("
            + "id integer primary key autoincrement, "
            + "text_sub text, "
            + "text_data text, "
            + "text_account text, "
            + "text_major text)";
    public static final String CREATE_NOTE = "create table Note("
            + "id integer primary key autoincrement, "
            + "account text, "
            + "biji text)";
    private Context mContext;
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
        mContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_USER);
        db.execSQL(CREATE_TIME);
        db.execSQL(CREATE_NOTE);
        Toast.makeText(mContext, "create succeeded", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
