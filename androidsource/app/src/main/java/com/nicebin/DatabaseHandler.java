package com.nicebin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class DatabaseHandler extends SQLiteOpenHelper {

    private static String DB_PATH ;
    private static String DB_NAME="locations.sqlite" ;




    private SQLiteDatabase Locationdb;
    private Context DBContext;

    public DatabaseHandler(Context context) {
        super(context, DB_NAME, null, 1);
        this.DBContext = context;

        DB_PATH = "/data/data/"+ context.getPackageName() +"/databases/";
        try {
            createDataBase();
        } catch (IOException e) {
        }

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void openDataBase(){
        try {
            String dbPath = DB_PATH+DB_NAME ;
            Locationdb = SQLiteDatabase.openDatabase(dbPath, null,SQLiteDatabase.OPEN_READWRITE);
        } catch (Exception e) {
        }

    }

    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist) {
        } else {

            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {

        }

        if (checkDB != null) {
            checkDB.close();
        }

        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = DBContext.getAssets().open(DB_NAME);

        String outFileName = DB_PATH + DB_NAME;

        File file = new File(outFileName);
        if(!file.exists())
            file.createNewFile();

        OutputStream myOutput = new FileOutputStream(file);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }
    public void closeDatabase()
    {
        try {
            Locationdb.close();
            close();
        } catch (Exception e) {
        }
    }

    public ArrayList<String>[] getAllLocations()
    {
        openDataBase();
        ArrayList<String>[] group = new ArrayList[4];

        group[0] = new ArrayList<>();
        group[1] = new ArrayList<>();
        group[2] = new ArrayList<>();
        group[3] = new ArrayList<>();

        int counter = 0;

        if(Locationdb !=null)
        {

            try {
                Cursor cursor = Locationdb.rawQuery("SELECT * FROM bins", null);
                if (cursor.moveToFirst()){
                    do{
                        group[0].add(counter, cursor.getString(cursor.getColumnIndex("name")));
                        group[1].add(counter, cursor.getString(cursor.getColumnIndex("latitude")));
                        group[2].add(counter, cursor.getString(cursor.getColumnIndex("longitude")));
                        group[3].add(counter, cursor.getString(cursor.getColumnIndex("full")));

                    }while(cursor.moveToNext());
                }
                cursor.close();

            } catch (Exception e) {
                Log.e("=================",e.toString());
            }

        }
        closeDatabase();
        return group;
    }

    public ArrayList<String>[] getAllLeaders()
    {
        openDataBase();
        ArrayList<String>[] group = new ArrayList[2];

        group[0] = new ArrayList<>();
        group[1] = new ArrayList<>();


        int counter = 0;

        if(Locationdb !=null)
        {

            try {
                Cursor cursor = Locationdb.rawQuery("SELECT * FROM leaderboard ORDER BY score DESC", null);
                if (cursor.moveToFirst()){
                    do{
                        group[0].add( cursor.getString(cursor.getColumnIndex("name")));
                        group[1].add( cursor.getString(cursor.getColumnIndex("score")));
                    }while(cursor.moveToNext());
                }
                cursor.close();

            } catch (Exception e) {
                Log.e("=================",e.toString());
            }

        }
        closeDatabase();
        return group;
    }


}