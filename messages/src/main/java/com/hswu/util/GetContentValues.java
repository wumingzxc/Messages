package com.hswu.util;

import com.hswu.bean.BaseBean;
import com.hswu.bean.CreditCard;
import com.hswu.bean.Favorite;
import com.hswu.bean.Note;

import android.content.ContentValues;

public class GetContentValues {


	public static ContentValues getContentValues(BaseBean baseBean)
	{
		ContentValues cv = new ContentValues();
		if (baseBean instanceof  CreditCard) {
			CreditCard creditCard = (CreditCard) baseBean;
			cv.put(URIField.CREDITCARD_BANKNAME, creditCard.getBankName());
			cv.put(URIField.CREDITCARD_CARDNAME,creditCard.getCardName());
			cv.put(URIField.CREDITCARD_CARDNUMBER, creditCard.getCardNumber());
			cv.put(URIField.CREDITCARD_CVV2, creditCard.getCvv2());
			cv.put(URIField.CREDITCARD_INDATE, creditCard.getIndate());
			cv.put(URIField.CREDITCARD_LIMITS, creditCard.getLimit());
		}
		if (baseBean instanceof Note)
		{
			Note note = (Note) baseBean;
			cv.put(URIField.SAFEREMARK_REMARKTITLE, note.getNoteName());
			cv.put(URIField.SAFEREMARK_REMARKCONTENT, note.getNoteContent());
		}

		if (baseBean instanceof Favorite)
		{
			Favorite favorite = (Favorite) baseBean;
			cv.put(URIField.FAVORITE_ITEMID, favorite.getItemId());
			cv.put(URIField.FAVORITE_ITEMTYPE,favorite.getItemType());
		}
		return cv;
	}
}
