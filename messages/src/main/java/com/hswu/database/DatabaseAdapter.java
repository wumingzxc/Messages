package com.hswu.database;

import com.hswu.bean.BaseBean;
import com.hswu.rowmapper.RowMapper;
import com.hswu.util.CursorTo;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.List;

public class DatabaseAdapter {

	private ContentResolver contentResolver;
	private volatile static DatabaseAdapter instance;
	
	private  DatabaseAdapter(Context context)
	{
		contentResolver = context.getContentResolver();
	}
		
	public static  DatabaseAdapter getInstance(Context context)
	{
		if (null == instance) {
			synchronized(DatabaseAdapter.class)
			{
				if (null == instance)
				{
					instance = new DatabaseAdapter(context);
				}
			}
		}
		return instance;
	}
	
	
	public void insertData(Uri uri,ContentValues values)
	{
		contentResolver.insert(uri, values);
	}
	
	public List<? extends BaseBean> queryDatas(Uri uri, RowMapper rowMapper)
	{
		return CursorTo.cursorToBaseBeans(contentResolver.query(uri, null, null, null, null),rowMapper);

	}

	public BaseBean queryData(Uri uri, RowMapper rowMapper, String selection, String[] selectionArgs)
	{
		Cursor c = contentResolver.query(uri, null, selection, selectionArgs, null);
		if (c != null && c.moveToNext())
		{
			BaseBean baseBean = rowMapper.mapRow(c);
			c.close();
			return baseBean;
		}

		return null;
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
