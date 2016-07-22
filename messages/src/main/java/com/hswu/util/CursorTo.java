package com.hswu.util;

import java.util.ArrayList;
import java.util.List;

import com.hswu.bean.CreditCard;

import android.database.Cursor;

public class CursorTo {

	public static List<CreditCard> cursorToCreditCard(Cursor c) {
		List<CreditCard> cards = new ArrayList<CreditCard>();

		if (c != null) {
			while (c.moveToNext()) {

				CreditCard card = new CreditCard();
				card.setId(c.getInt(c.getColumnIndex("id")));
				card.setBankName(c.getString(c.getColumnIndex("bankname")));
				card.setCardName(c.getString(c.getColumnIndex("cardname")));
				card.setCardNumber(c.getString(c.getColumnIndex("cardnumber")));
				card.setCvv2(c.getString(c.getColumnIndex("cvv2")));
				card.setIndate(c.getString(c.getColumnIndex("indate")));
				card.setLimit(c.getString(c.getColumnIndex("limits")));
				cards.add(card);
			}
		}

		c.close();
		
		return cards;
	}

}
