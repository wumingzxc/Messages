package com.hswu.database;

import com.hswu.util.URIField;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class Provider extends ContentProvider {

	private PmSQLiteOpenHelper pmSQLiteOpenHelper;
	private SQLiteDatabase database;
	private static final UriMatcher uriMatcher;


	static {

		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(URIField.AUTOHORITY, URIField.TNAME_CREDITCARD, URIField.CREDITCARD_CODE);
		uriMatcher.addURI(URIField.AUTOHORITY, URIField.TNAME_LOGINMESSAGE, URIField.LOGINMESSAGE_CODE);
		uriMatcher.addURI(URIField.AUTOHORITY, URIField.TNAME_SAFEREMARK, URIField.SAFEREMARK_CODE);
		uriMatcher.addURI(URIField.AUTOHORITY, URIField.TNAME_FAVORITE, URIField.FAVORITE_CODE);

	}


	@Override
	public boolean onCreate() {
		pmSQLiteOpenHelper = new PmSQLiteOpenHelper(this.getContext());
		return true;
	}


	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

		database = pmSQLiteOpenHelper.getWritableDatabase();
		Cursor c = null;
		switch (uriMatcher.match(uri)) {

			case URIField.CREDITCARD_CODE:
				c = database.query(URIField.TNAME_CREDITCARD, projection, selection,selectionArgs,null,null,null);

				break;
			case URIField.LOGINMESSAGE_CODE:
				c = database.query(URIField.TNAME_LOGINMESSAGE, projection, selection,selectionArgs,null,null,null);

				break;
			case URIField.SAFEREMARK_CODE:

				c = database.query(URIField.TNAME_SAFEREMARK, projection, selection,selectionArgs,null,null,null);

				break;
			case URIField.FAVORITE_CODE:

				c = database.query(URIField.TNAME_FAVORITE, projection, selection,selectionArgs,null,null,null);

				break;
		}
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}


	@Override
	public String getType(Uri uri) {
		return null;
	}



	@Override
	public Uri insert(Uri uri, ContentValues values) {

		database = pmSQLiteOpenHelper.getWritableDatabase();
		long rowId;
		Uri newUri = null;

		if (uriMatcher.match(uri)!= URIField.CREDITCARD_CODE && uriMatcher.match(uri)!= URIField.LOGINMESSAGE_CODE && uriMatcher.match(uri)!= URIField.SAFEREMARK_CODE&&uriMatcher.match(uri)!= URIField.FAVORITE_CODE) {
			throw new IllegalArgumentException("uri错误"+uri);
		}

		switch (uriMatcher.match(uri)) {

			case URIField.CREDITCARD_CODE:
				rowId = database.insert(URIField.TNAME_CREDITCARD, null, values);

				if (rowId > 0) {
					newUri = ContentUris.withAppendedId(URIField.CREDITCARD_URI, rowId);
				}
				break;
			case URIField.LOGINMESSAGE_CODE:
				rowId = database.insert(URIField.TNAME_LOGINMESSAGE, null, values);

				if (rowId > 0) {
					newUri = ContentUris.withAppendedId(URIField.LOGINMESSAGE_URI, rowId);
				}
				break;
			case URIField.SAFEREMARK_CODE:
				rowId = database.insert(URIField.TNAME_SAFEREMARK, null, values);

				if (rowId > 0) {
					newUri = ContentUris.withAppendedId(URIField.SAFEREMARK_URI, rowId);
				}
				break;
			case URIField.FAVORITE_CODE:
				rowId = database.insert(URIField.TNAME_FAVORITE, null, values);

				if (rowId > 0) {
					newUri = ContentUris.withAppendedId(URIField.FAVORITE_URI, rowId);
				}
				break;
		}

		getContext().getContentResolver().notifyChange(newUri, null);

		if (newUri != null) {
			return newUri;
		}else{
			throw new IllegalArgumentException("uri错误"+uri);
		}
	}


	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {

		database = pmSQLiteOpenHelper.getWritableDatabase();
		int i = 0;
		switch (uriMatcher.match(uri)) {

			case URIField.CREDITCARD_CODE:
				i = database.delete(URIField.TNAME_CREDITCARD, selection, selectionArgs);

				break;
			case URIField.LOGINMESSAGE_CODE:
				i = database.delete(URIField.TNAME_LOGINMESSAGE, selection, selectionArgs);
				break;
			case URIField.SAFEREMARK_CODE:
				i = database.delete(URIField.TNAME_SAFEREMARK, selection, selectionArgs);
				break;
			case URIField.FAVORITE_CODE:
				i = database.delete(URIField.TNAME_FAVORITE, selection, selectionArgs);
				break;
		}
		return i;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

		database = pmSQLiteOpenHelper.getWritableDatabase();
		int i = 0;
		switch (uriMatcher.match(uri)) {

			case URIField.CREDITCARD_CODE:
				i = database.update(URIField.TNAME_CREDITCARD, values, selection, selectionArgs);

				break;
			case URIField.LOGINMESSAGE_CODE:
				i = database.update(URIField.TNAME_LOGINMESSAGE, values, selection, selectionArgs);

				break;
			case URIField.SAFEREMARK_CODE:
				i = database.update(URIField.TNAME_SAFEREMARK, values, selection, selectionArgs);
				break;
			case URIField.FAVORITE_CODE:
				i = database.update(URIField.TNAME_FAVORITE, values, selection, selectionArgs);
				break;
		}
		return i;
	}

}
