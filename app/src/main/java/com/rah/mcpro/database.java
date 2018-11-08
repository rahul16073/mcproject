package com.rah.mcpro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database extends SQLiteOpenHelper {
    public static final String database="data.db";
    public static final String table="users";
    public static final String COL1="ID";
    public static final String COL2="Username";
    public static final String COL3="Password";
    public static final String COL4="Phone";
    public  static final String Blocked="false";

    public database(Context context) {
        super(context, database, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
      sqLiteDatabase.execSQL("CREATE TABLE "+table+" (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
              "Username TEXT,Password TEXT,Phone TEXT,Blocked TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
       sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+table);
       onCreate(sqLiteDatabase);
    }
    public boolean add(String user,String password,String phone,String blocked){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Username",user);
        contentValues.put("Password",password);
        contentValues.put("Phone",phone);
        contentValues.put("Blocked",blocked);
        long result=db.insert(table,null,contentValues);
        if(result==-1){
            return false;
        }
        else {
            return true;
        }
    }
    public int login(String name,String pass){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.query(table,null,null,null,null,null,null);
        int usr=0;
        while (c.moveToNext()){
            String user=c.getString(c.getColumnIndex("Username"));
            String password=c.getString(c.getColumnIndex("Password"));
            if(user.equals(name)){
                usr=1;
            }
            if(user.equals(name) && password.equals(pass)){
                return 2;
            }
        }
        if(usr==1){
            return 1;
        }
        else {
            return 0;
        }
    }
    public boolean CheckUserRegistration(String user){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.query(table,null,null,null,null,null,null);
        while (c.moveToNext()){
            String username=c.getString(c.getColumnIndex("Username"));
            if(user.equals(username)){
                return true;
            }
        }
        return false;
    }

}
