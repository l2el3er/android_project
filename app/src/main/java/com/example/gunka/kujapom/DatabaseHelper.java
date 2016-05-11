package com.example.gunka.kujapom;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by gunka on 14-Mar-16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "KJPs.db";

    public static final String TABLE_NAME = "Exercise";
    //declare Fields name
    public static final String FIELD_1 = "ExID";
    public static final String FIELD_2 = "ExName";
    public static final String FIELD_3 = "ExType";
    public static final String FIELD_4 = "ExCal";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = getAllData();
        if (res.getCount() == 0) {
            insertData("กระโดดเชือก", "1", "780");
            insertData("การเต้น Zumba", "1", "500");
            insertData("ขี่จักรยาน", "1", "250-600");
            insertData("ซักผ้าด้วยมือ", "2", "240");
            insertData("ตัดหญ้า", "1", "250-300");

            insertData("ทำงานบ้าน", "2", "150-250");
            insertData("ทำสวน", "1", "300-450");
            insertData("นอนหลับ", "2", "75");
            insertData("นั่งดูทีวี", "2", "100");
            insertData("นั่งทำงานใช้สมอง", "2", "110");
            insertData("บาสเก็ตบอล", "1", "360-660");
            insertData("ปูที่นอน", "2", "135");

            insertData("มวยไทย", "1", "800");
            insertData("รีดผ้า", "2", "150");
            insertData("วิ่งเร็ว", "1", "900-1200");
            insertData("วิ่งเหยาะๆ", "1", "600-750");
            insertData("ว่ายน้ำ", "1", "260-750");
            insertData("เดินขึ้นบันได", "2", "600-1080");

            insertData("เดินช้า", "1", "150");
            insertData("เดินธรรมดา", "1", "300");
            insertData("เต้นรำ", "1", "350");
            insertData("เล่นวอลเล่ย์บอล", "1", "300");
            insertData("เล่นฮูล่าฮูป", "1", "430");
            insertData("เล่นเทนนิสคู่", "1", "360");

            insertData("เล่นเทนนิสเดี่ยว", "1", "480");
            insertData("เล่นแบตมินตัน", "1", "350");
            insertData("เล่นโบว์ลิ่ง", "1", "400");
            insertData("แอโรบิค", "1", "600");

        }


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ExID INTEGER PRIMARY KEY,ExName TEXT,ExType TEXT,ExCal TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String ExName, String ExType, String ExCal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_2, ExName);
        contentValues.put(FIELD_3, ExType);
        contentValues.put(FIELD_4, ExCal);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public Cursor getById(String ExID) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " WHERE ExID = ?", new String[]{ExID});
        return res;
    }

    public boolean updateData(String ExID, String ExName, String ExCal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_2, ExName);
        contentValues.put(FIELD_3, ExCal);
        db.update(TABLE_NAME, contentValues, "EmpID = ?", new String[]{ExID});
        return true;
    }

    public Integer deleteData(String ExID) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ExID = ?", new String[]{ExID});
    }

}
