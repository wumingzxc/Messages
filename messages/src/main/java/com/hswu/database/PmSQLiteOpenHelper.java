package com.hswu.database;

import com.hswu.util.URIField;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PmSQLiteOpenHelper extends SQLiteOpenHelper {

	private static final String CREATE_CREDITCARD = " create table "+URIField.TNAME_CREDITCARD
			+" (id integer primary key, "
			+URIField.CREDITCARD_BANKNAME+" varchar, "
			+URIField.CREDITCARD_CARDNAME+" varchar, "
			+URIField.CREDITCARD_CARDNUMBER+" varchar, "
			+URIField.CREDITCARD_CVV2+" varchar, "
			+URIField.CREDITCARD_INDATE+" varchar, "
			+URIField.CREDITCARD_LIMITS+" varchar )";

	private static final String CREATE_LOGINMESSAGE = " create table "+URIField.TNAME_LOGINMESSAGE
			+" (id integer primary key, "
			+URIField.LOGINMESSAGE_LOGINTITLE+" varchar, "
			+URIField.LOGINMESSAGE_USERNAME+" varchar, "
			+URIField.LOGINMESSAGE_PASSWORD+" varchar, "
			+URIField.LOGINMESSAGE_URL+" varchar, "
			+URIField.LOGINMESSAGE_REMARK+" varchar )";

	private static final String CREATE_SAFEREMARK = " create table "+URIField.TNAME_SAFEREMARK
			+" (id integer primary key, "
			+URIField.SAFEREMARK_REMARKTITLE+" varchar, "
			+URIField.SAFEREMARK_REMARKCONTENT+" varchar )";

	private static final String CREATE_FAVORITE = " create table "+URIField.TNAME_FAVORITE
			+" (id integer primary key, "
			+URIField.FAVORITE_ITEMID+" integer, "
			+URIField.FAVORITE_ITEMTYPE+" varchar )";


	public PmSQLiteOpenHelper(Context context) {
		super(context, URIField.DBNAME, null, URIField.VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL(CREATE_CREDITCARD);
		db.execSQL(CREATE_LOGINMESSAGE);
		db.execSQL(CREATE_SAFEREMARK);
		db.execSQL(CREATE_FAVORITE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


	}

}
