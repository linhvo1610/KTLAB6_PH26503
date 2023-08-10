package com.example.ktlab6_ph26503.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ktlab6_ph26503.DAO.DAO_SP;

public class myHelper extends SQLiteOpenHelper {
    static final String db_NAME = "QUANLY.db";
    static final int VERSION = 3;

    public myHelper(Context context) {
        super(context, db_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DAO_SP.CREATE_TB_SP);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DAO_SP.TB_NAME);




    }
}