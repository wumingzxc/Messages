package com.hswu.util;

import com.hswu.bean.BaseBean;
import com.hswu.bean.CreditCard;
import com.hswu.bean.Note;

import android.content.ContentValues;

public class GetContentValues {


	public static ContentValues getContentValues(BaseBean baseBean)
	{
		ContentValues cv = new ContentValues();
		if (baseBean instanceof  CreditCard) {
			CreditCard creditCard = (CreditCard) baseBean;
			cv.put("bankname", creditCard.getBankName());
			cv.put("cardname", creditCard.getCardName());
			cv.put("cardnumber", creditCard.getCardNumber());
			cv.put("cvv2", creditCard.getCvv2());
			cv.put("indate", creditCard.getIndate());
			cv.put("limits", creditCard.getLimit());
		}

		if (baseBean instanceof Note)
		{
			Note note = (Note) baseBean;
			cv.put("remarktitle", note.getNoteName());
			cv.put("remarkcontent", note.getNoteContent());
		}
		return cv;
	}
}
