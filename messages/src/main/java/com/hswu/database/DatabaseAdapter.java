package com.hswu.database;

import com.hswu.bean.BaseBean;
import com.hswu.rowmapper.CreditCardRowMapper;
import com.hswu.rowmapper.RowMapper;
import com.hswu.util.CursorTo;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import java.util.List;

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
	
	public List<? extends BaseBean> queryData(Uri uri, RowMapper rowMapper)
	{
		return CursorTo.cursorToBaseBean(contentResolver.query(uri, null, null, null, null),rowMapper);
		
	}

	public int updateData(Uri uri,ContentValues values, String selection, String[] selectionArgs)
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
