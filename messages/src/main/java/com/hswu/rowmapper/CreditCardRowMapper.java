package com.hswu.rowmapper;

import android.database.Cursor;

import com.hswu.bean.BaseBean;
import com.hswu.bean.CreditCard;
import com.hswu.util.URIField;

/**
 * Created by HandsomeWu on 2016/7/23.
 */

public class CreditCardRowMapper implements RowMapper {
    @Override
    public BaseBean mapRow(Cursor c) {
        CreditCard card =  card = new CreditCard();;
        card.setId(c.getInt(c.getColumnIndex("id")));
        card.setBankName(c.getString(c.getColumnIndex(URIField.CREDITCARD_BANKNAME)));
        card.setCardName(c.getString(c.getColumnIndex(URIField.CREDITCARD_CARDNAME)));
        card.setCardNumber(c.getString(c.getColumnIndex(URIField.CREDITCARD_CARDNUMBER)));
        card.setCvv2(c.getString(c.getColumnIndex(URIField.CREDITCARD_CVV2)));
        card.setIndate(c.getString(c.getColumnIndex(URIField.CREDITCARD_INDATE)));
        card.setLimit(c.getString(c.getColumnIndex(URIField.CREDITCARD_LIMITS)));
        return card;
    }
}
