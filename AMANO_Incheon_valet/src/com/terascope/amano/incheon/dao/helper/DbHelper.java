package com.terascope.amano.incheon.dao.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.terascope.amano.R;
import com.terascope.amano.incheon.dto.RectDto;

public class DbHelper {
	private static String TAG = "DBLOG";
	private static final String DATABASE_NAME = "AmanoInchen.db";
	private static final int DATABASE_VERSION = 1;
	public static SQLiteDatabase mDB;
	private DatabaseHelper mDBHelper;
	private Context mContext;
	private RectDto rec;
	public DbHelper(Context context){
		this.mContext = context;
		//open();
	}
	
	public DbHelper open() throws SQLException {
		mDBHelper = new DatabaseHelper(mContext, DATABASE_NAME, null, DATABASE_VERSION);
		mDB = mDBHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		mDB.close();
	}
	public void transation(){
		mDB.beginTransaction();
	}
	public void t(){
		mDB.setTransactionSuccessful();
		mDB.endTransaction();
		
	}
	// select DB
	public Cursor selectColumn(String query){
		Cursor c = mDB.rawQuery(query , null);		
		return c;
	}
	
	// Insert DB
	@SuppressLint("SimpleDateFormat")
	public long insertColumn(String tableName, ContentValues values){		
		long result = mDB.insert(tableName, null, values);		
		return result;
	}
	
	// Update DB
	public boolean updateColumn(String tableName, ContentValues values, String whares){
		return mDB.update(tableName, values, whares, null) > 0;
	}
	
		
	// Delete ID
	public boolean deleteColumn(String tableName, long id){
		//Log.i(TAG,"delete item id : " + id);
		return mDB.delete(tableName, "id="+id, null) > 0;
	}
	
	// Delete all
	public boolean deleteAll(String tableName){
		//Log.i(TAG,"delete item id : " + id);
		return mDB.delete(tableName, null, null) > 0;
	}
	
	public long insertAndReplace(String tableName, ContentValues values){		
		long result = mDB.insertWithOnConflict(tableName, null, values, SQLiteDatabase.CONFLICT_REPLACE);		
		return result;
	}
	
	private class DatabaseHelper extends SQLiteOpenHelper{
	
		private Context mContext;
		
		public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
			super(context, name, factory, version);
			this.mContext = context;
		}
	
		@Override
		public void onCreate(SQLiteDatabase db) {
			
			db.execSQL(mContext.getResources().getString(R.string.db_table_ancm));
			db.execSQL(mContext.getResources().getString(R.string.db_table_carinfo));
			db.execSQL(mContext.getResources().getString(R.string.db_table_device));
			db.execSQL(mContext.getResources().getString(R.string.db_table_login));
			db.execSQL(mContext.getResources().getString(R.string.db_table_mm));
			db.execSQL(mContext.getResources().getString(R.string.db_table_rect));
			db.execSQL(mContext.getResources().getString(R.string.db_table_user));
			
		}
	
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			//db.execSQL("DROP TABLE IF EXISTS TEMP");
			switch (oldVersion) {
			case 1:
				break;
			default:
				break;
			}
		}
	}

}
