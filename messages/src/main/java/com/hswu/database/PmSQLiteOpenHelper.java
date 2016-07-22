package com.hswu.database;

import com.hswu.util.URIField;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PmSQLiteOpenHelper extends SQLiteOpenHelper {

	public PmSQLiteOpenHelper(Context context) {
		super(context, URIField.DBNAME, null, URIField.VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL("create table creditcard(id integer primary key,bankname varchar,cardname varchar,cardnumber varchar, cvv2 varchar, indate varchar, limits varchar )");
		db.execSQL("create table loginmessage(id integer primary key,logintitle varchar,username varchar,password varchar,url varchar,remark varchar)");
		db.execSQL("create table saferemarks(id integer primary key,remarktitle varchar,remarkcontent varchar)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


	}

}
