package com.example.android.navigationdrawerexample;

import java.util.List;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	
 public String TABLE;

  // for category table
  public static final String TABLE_BOOK = "book";
  public static final String COLUMN_BOOK_ID = "_id";
  public static final String COLUMN_BOOK_NAME = "name";
  public static final String COLUMN_BOOK_COVER = "cover";
  public static final String COLUMN_BOOK_LAT = "lat";
  public static final String COLUMN_BOOK_LNG = "lng";
  public static final String COLUMN_BOOK_TIME = "time";
  public static final String COLUMN_BOOK_DATE = "date";
  

  private static final String DATABASE_NAME = "SmartBook.db";
  private static final int DATABASE_VERSION = 1;
  
  
//Database creation sql statement for interatcion table
 private static final String DATABASE_CREATE_CATEGORY = "create table "
     + TABLE_BOOK + "(" + COLUMN_BOOK_ID
     + " integer primary key autoincrement, " + COLUMN_BOOK_NAME
     + " text not null, "+	COLUMN_BOOK_COVER
     + " text not null, "+  COLUMN_BOOK_LAT
     + " text not null, "+  COLUMN_BOOK_LNG
     + " text not null, "+  COLUMN_BOOK_TIME
     + " text not null, "+  COLUMN_BOOK_DATE
     + " text not null);";

  public MySQLiteHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
	  	database.execSQL(DATABASE_CREATE_CATEGORY);
    
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(MySQLiteHelper.class.getName(),
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE);
	    onCreate(db);
  }

} 