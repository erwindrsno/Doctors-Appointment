package com.example.gws;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class SQLiteManagerDokter extends SQLiteOpenHelper{
    private static SQLiteManagerDokter sqLiteManagerDokter;

    private static final String DATABASE_NAME = "gwsDBDokter";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME_DOKTER = "Dokter";

    private static final String ID_FIELD_DOKTER = "iddokter";
    private static final String NAMA_FIELD_DOKTER = "namadokter";
    private static final String SPESIALIS_FIELD_DOKTER = "spesialisdokter";
    private static final String NOMORHP_FIELD_DOKTER = "nomorhpdokter";
    private static final String DETAIL_FIELD_DOKTER = "detaildokter";

    public SQLiteManagerDokter(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static SQLiteManagerDokter instanceOfDatabase(Context context){
        if(sqLiteManagerDokter == null){
            sqLiteManagerDokter = new SQLiteManagerDokter(context);
        }
        return sqLiteManagerDokter;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME_DOKTER + " (" +
                ID_FIELD_DOKTER + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAMA_FIELD_DOKTER + " TEXT, " +
                SPESIALIS_FIELD_DOKTER + " TEXT, " +
                NOMORHP_FIELD_DOKTER + " TEXT, " +
                DETAIL_FIELD_DOKTER + " TEXT); " ;
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_DOKTER );
        onCreate(sqLiteDatabase);
    }

    public void addDokterToDatabase(String nama, String spesialis, String nomorHP, String detail){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAMA_FIELD_DOKTER, nama);
        cv.put(SPESIALIS_FIELD_DOKTER, spesialis);
        cv.put(NOMORHP_FIELD_DOKTER, nomorHP);
        cv.put(DETAIL_FIELD_DOKTER, detail);

        long eksekusi = sqLiteDatabase.insert(TABLE_NAME_DOKTER, null, cv);
        Log.d("debugdatabase", eksekusi+"");
    }


    public Cursor loadDokters(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME_DOKTER;
        Cursor result = sqLiteDatabase.rawQuery(query, null);

        return result;
    }

    public void editDokterDatabase(int id,String nama, String spesialis, String nomorHP, String detail){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NAMA_FIELD_DOKTER, nama);
        cv.put(SPESIALIS_FIELD_DOKTER, spesialis);
        cv.put(NOMORHP_FIELD_DOKTER, nomorHP);
        cv.put(DETAIL_FIELD_DOKTER, detail);

        sqLiteDatabase.update(TABLE_NAME_DOKTER, cv, ID_FIELD_DOKTER+" =?", new String[] {String.valueOf(id)});
    }

    public void deleteDokterDatabase(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.delete(TABLE_NAME_DOKTER, ID_FIELD_DOKTER+"=?", new String[]{Integer.toString(id)});
    }
}
