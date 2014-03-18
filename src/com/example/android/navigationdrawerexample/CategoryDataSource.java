package com.example.android.navigationdrawerexample;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CategoryDataSource {

//	  // Database fields
//	  private SQLiteDatabase database;
//	  private MySQLiteHelper dbHelper;
//	  private String[] allColumns = { MySQLiteHelper.COLUMN_BOOK_ID,
//			  						  MySQLiteHelper.COLUMN_BOOK_NAME,
//			  						  MySQLiteHelper.COLUMN_BOOK_COVER,
//			  						  MySQLiteHelper.COLUMN_BOOK_LAT,
//			  						  MySQLiteHelper.COLUMN_BOOK_LNG,
//			  						  MySQLiteHelper.COLUMN_BOOK_TIME,
//			  						  MySQLiteHelper.COLUMN_BOOK_DATE,};
//
//	  public CategoryDataSource(Context context) {
//	    dbHelper = new MySQLiteHelper(context);
//	  }
//
//	  public void open() throws SQLException {
//	    database = dbHelper.getWritableDatabase();
//	  }
//
//	  public void close() {
//		  dbHelper.close();
//	  }
//	  
//	  public void createBook(String name, String cover, String lat, String lng, String time, String date) {
//		  
//		    ContentValues values = new ContentValues();
//		    values.put(MySQLiteHelper.COLUMN_BOOK_NAME, name);
//		    values.put(MySQLiteHelper.COLUMN_BOOK_COVER, cover);
//		    values.put(MySQLiteHelper.COLUMN_BOOK_LAT, lat);
//		    values.put(MySQLiteHelper.COLUMN_BOOK_LNG, lng);
//		    values.put(MySQLiteHelper.COLUMN_BOOK_TIME, time);
//		    values.put(MySQLiteHelper.COLUMN_BOOK_DATE, date);
//		    long insertId = database.insert(MySQLiteHelper.TABLE_BOOK, null,
//		        values);
//	  }
//	  private Book cursorToBook(Cursor cursor) {
//		  	if( cursor.getCount()>0){
//		  		Book book = new Book(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
//		  		return book;
//		  	}
//		  	return null;
//  	  }
//	  
//	  public boolean isTableExists(){
//		  Cursor c = null;
//		  boolean tableExists = false;
//		  try
//		  {
//		      c = database.query(MySQLiteHelper.TABLE_BOOK, allColumns,
//		          null, null, null, null, null);
//		      if (c.getCount() > 0)
//		          tableExists = true;
//		  }
//		  catch (Exception e) {
//		  }
//		  return tableExists;
//	  }
//	  
////	  public List<Book> findCategoryId(String lat, String lat){
////		  List<Book> result = new ArrayList<Book>();
//////		  Cursor cursor = database.query(MySQLiteHelper.TABLE_CATEGORY,
//////			        allColumns, MySQLiteHelper.COLUMN_CATEGORY_NAME+ " LIKE "+"'%"+key+"%'" , null,
//////			        null, null, null);
////		  Cursor cursor = database.query(MySQLiteHelper.TABLE_BOOK,
////			        allColumns, MySQLiteHelper.COLUMN_CATEGORY_NAME+ " = "+"'"+key+"'" , null,
////			        null, null, null);
////		  while (cursor.moveToNext()){
////			  Category category = cursorToCategory(cursor);
////			  result.add(category);
////		  }
////		  
////		  cursor.close();
////		  return result;
////	  }
	  
	  
	} 