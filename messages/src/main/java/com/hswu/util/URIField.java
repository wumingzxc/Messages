package com.hswu.util;

import android.net.Uri;

public class URIField {

	public static final String DBNAME = "messages";
	public static final int VERSION = 1;

	public static final String TNAME_CREDITCARD = "creditcard";
	public static final String CREDITCARD_BANKNAME="bankname";
	public static final String CREDITCARD_CARDNAME="cardname";
	public static final String CREDITCARD_CARDNUMBER="cardnumber";
	public static final String CREDITCARD_CVV2="cvv2";
	public static final String CREDITCARD_INDATE="indate";
	public static final String CREDITCARD_LIMITS="limits";

	public static final String TNAME_LOGINMESSAGE = "loginmessage";
	public static final String LOGINMESSAGE_LOGINTITLE="logintitle";
	public static final String LOGINMESSAGE_USERNAME="username";
	public static final String LOGINMESSAGE_PASSWORD="password";
	public static final String LOGINMESSAGE_URL="url";
	public static final String LOGINMESSAGE_REMARK="remark";

	public static final String TNAME_SAFEREMARK ="saferemarks";
	public static final String SAFEREMARK_REMARKTITLE="remarktitle";
	public static final String SAFEREMARK_REMARKCONTENT="remarkcontent";


	public static final String TNAME_FAVORITE = "favorite";
	public static final String FAVORITE_ITEMID = "itemid";
	public static final String FAVORITE_ITEMTYPE = "itemtype";



	public static final String AUTOHORITY = "com.hswu.database.provider";
	public static final int CREDITCARD_CODE = 0;
	public static final int LOGINMESSAGE_CODE = 1;
	public static final int SAFEREMARK_CODE = 2;
	public static final int FAVORITE_CODE = 3;
	public static final Uri CREDITCARD_URI = Uri.parse("content://" + AUTOHORITY+ "/creditcard");
	public static final Uri LOGINMESSAGE_URI = Uri.parse("content://" + AUTOHORITY+ "/loginmessage");
	public static final Uri SAFEREMARK_URI = Uri.parse("content://" + AUTOHORITY+ "/saferemarks");
	public static final Uri FAVORITE_URI = Uri.parse("content://" + AUTOHORITY+ "/favorite");
	
}
