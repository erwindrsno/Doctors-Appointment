package com.example.gws;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteManagerPertemuan extends SQLiteOpenHelper {
    private static SQLiteManagerPertemuan sqLiteManagerPertemuan;

    private static final String DATABASE_NAME = "gwsDBPertemuan";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME_PERTEMUAN = "Pertemuan";

    private static final String ID_FIELD_PERTEMUAN = "idpertemuan";
    private static final String TANGGAL_FIELD_PERTEMUAN = "tanggal";
    private static final String WAKTU_FIELD_PERTEMUAN = "waktu";
    private static final String NAMAPASIEN_FIELD_PERTEMUAN = "namapasien";
    private static final String NAMADOKTER_FIELD_PERTEMUAN = "namadokter";
    private static final String KELUHAN_FIELD_PERTEMUAN = "keluhan";
    private static final String SPESIALIS_FIELD_PERTEMUAN = "spesialis";
    private static final String GENDER_FIELD_PERTEMUAN = "gender";
    private static final String DONE_FIELD_PERTEMUAN = "done";

    public SQLiteManagerPertemuan(@Nullable Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME_PERTEMUAN + " (" +
                ID_FIELD_PERTEMUAN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TANGGAL_FIELD_PERTEMUAN + " TEXT, " +
                WAKTU_FIELD_PERTEMUAN + " TEXT, " +
                NAMAPASIEN_FIELD_PERTEMUAN + " TEXT, " +
                NAMADOKTER_FIELD_PERTEMUAN + " TEXT, " +
                KELUHAN_FIELD_PERTEMUAN + " TEXT, "+
                SPESIALIS_FIELD_PERTEMUAN + " TEXT, "+
                GENDER_FIELD_PERTEMUAN + " TEXT, "+
                DONE_FIELD_PERTEMUAN + " TEXT); ";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PERTEMUAN );
        onCreate(sqLiteDatabase);
    }

    public void addPertemuanDatabase(String tanggal, String waktu, String namapasien, String namadokter, String keluhan, String spesialis, String gender, String done){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TANGGAL_FIELD_PERTEMUAN, tanggal);
        cv.put(WAKTU_FIELD_PERTEMUAN, waktu);
        cv.put(NAMAPASIEN_FIELD_PERTEMUAN, namapasien);
        cv.put(NAMADOKTER_FIELD_PERTEMUAN, namadokter);
        cv.put(KELUHAN_FIELD_PERTEMUAN, keluhan);
        cv.put(SPESIALIS_FIELD_PERTEMUAN, spesialis);
        cv.put(GENDER_FIELD_PERTEMUAN, gender);
        cv.put(DONE_FIELD_PERTEMUAN, done);

        long eks = database.insert(TABLE_NAME_PERTEMUAN, null, cv);
    }

    public Cursor loadPertemuan(){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME_PERTEMUAN;

        Cursor result = database.rawQuery(query, null);
        return result;
    }

    public void deletePertemuanDatabase(int id){
        SQLiteDatabase database = this.getWritableDatabase();

        database.delete(TABLE_NAME_PERTEMUAN, ID_FIELD_PERTEMUAN+" =? ", new String[]{Integer.toString(id)});
    }
}
