package com.hswu.database;

import com.hswu.util.CursorTo;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

public class DatabaseAdapter {

	private ContentResolver contentResolver;
	private static DatabaseAdapter instance;
	
	private  DatabaseAdapter(Context context)
	{
		contentResolver = context.getContentResolver();
	}
		
	public static synchronized DatabaseAdapter getInstance(Context context)
	{
		if (null == instance) {
			instance = new DatabaseAdapter(context);
		}
		return instance;
	}
	
	
	public void insertData(Uri uri,ContentValues values)
	{
		contentResolver.insert(uri, values);
	}
	
	public Object queryData(Uri uri)
	{
		return CursorTo.cursorToCreditCard(contentResolver.query(uri, null, null, null, null));
		
	}public int updateData(Uri uri,ContentValues values, String selection, String[] selectionArgs)
	{
		int i = 0;
		i = contentResolver.update(uri, values, selection, selectionArgs);
		return i;
	}
	
	public int deleteData(Uri uri, String selection, String[] selectionArgs)
	{
		int i = 0;
		i = contentResolver.delete(uri, selection, selectionArgs);
		return i;
	}
	
	
}
