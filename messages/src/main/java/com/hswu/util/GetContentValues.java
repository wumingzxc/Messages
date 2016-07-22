package com.hswu.util;

import com.hswu.bean.CreditCard;

import android.content.ContentValues;

public class GetContentValues {


	public static ContentValues getContentValues(CreditCard creditCard)
	{
		ContentValues cv = new ContentValues();
		cv.put("bankname", creditCard.getBankName());
		cv.put("cardname", creditCard.getCardName());
		cv.put("cardnumber", creditCard.getCardNumber());
		cv.put("cvv2", creditCard.getCvv2());
		cv.put("indate", creditCard.getIndate());
		cv.put("limits", creditCard.getLimit());
		return cv;
	}
}
