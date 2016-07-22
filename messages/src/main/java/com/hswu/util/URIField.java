package com.hswu.util;

import android.net.Uri;

public class URIField {

	public static final String DBNAME = "passwordmanager";
	public static final int VERSION = 1;
	
	
	public static final String AUTOHORITY = "com.hswu.database.provider";
	public static final String TNAME_CREDITCARD = "creditcard";
	public static final String TNAME_LOGINMESSAGE = "loginmessage";
	public static final String TNAME_SAFEREMARK ="saferemarks";
	
	public static final int CREDITCARD_CODE = 0;
	public static final int LOGINMESSAGE_CODE = 1;
	public static final int SAFEREMARK_CODE = 2;
	public static final Uri CREDITCARD_URI = Uri.parse("content://" + AUTOHORITY+ "/creditcard");
	public static final Uri LOGINMESSAGE_URI = Uri.parse("content://" + AUTOHORITY+ "/loginmessage");
	public static final Uri SAFEREMARK_URI = Uri.parse("content://" + AUTOHORITY+ "/saferemarks");
	
}
