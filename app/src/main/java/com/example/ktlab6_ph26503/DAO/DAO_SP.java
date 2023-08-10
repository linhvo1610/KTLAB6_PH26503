package com.example.ktlab6_ph26503.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ktlab6_ph26503.DTO.SP;
import com.example.ktlab6_ph26503.Database.myHelper;

import java.util.ArrayList;

public class DAO_SP {
        public static final String CREATE_TB_SP = "CREATE TABLE SP(ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,CONTENT TEXT,DATE TEXT, PRICE INTEGER);";
        public static final String TB_NAME = "SP";
        public static final String ID = "ID";
        public static final String CONTENT = "CONTENT";
        public static final String DATE = "DATE";
        public static final String PRICE = "PRICE";
        SQLiteDatabase database;
        myHelper MyHelper;

        public DAO_SP(Context context) {
            MyHelper = new myHelper(context);
        }

        public void opend() {
            database = MyHelper.getWritableDatabase();
        }

        public void close() {
            database.close();
        }
        public ArrayList<SP> selectAll() {
            ArrayList<SP> listsp = new ArrayList<SP>();
            String[] list_all = new String[]{SP.COL_NAME_ID, SP.COL_NAME_content, SP.COL_NAME_DATE, SP.COL_NAME_PRICE};
            Cursor cursor = database.query(SP.TB_NAME, list_all, null, null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(0);
                String content = cursor.getString(1);
                String date = cursor.getString(2);
                int price = cursor.getInt(3);

                SP sp = new SP();
                sp.setId(id);
                sp.setContent(content);
                sp.setDate(date);
                sp.setPrice(price);

                listsp.add(sp);
                cursor.moveToNext();

            }
            return listsp;

        }
    public long AddSP(SP sp) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DAO_SP.CONTENT, sp.getContent());
        contentValues.put(DAO_SP.DATE, sp.getDate());
        contentValues.put(DAO_SP.PRICE, sp.getPrice());
        try {
            if (database.insert(DAO_SP.TB_NAME, null, contentValues) == -1) {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }
    public int updateSP(SP sp){
        ContentValues contentValues = new ContentValues();

        contentValues.put(CONTENT, sp.getContent());
        contentValues.put(DATE,sp.getDate());
        contentValues.put(PRICE,sp.getPrice());
        try {
            if(database.update(TB_NAME,contentValues,ID+"=?", new String[]{sp.getId()+""})==-1){
                return -1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }
    public int deleteSP(SP sp){
        try {
            if(database.delete(TB_NAME,ID+"=?",  new String[]{sp.getId()+""})==-1){
                return -1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }





}

