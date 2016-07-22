package com.hswu.rowmapper;

import android.database.Cursor;

import com.hswu.bean.BaseBean;
import com.hswu.bean.CreditCard;

/**
 * Created by HandsomeWu on 2016/7/23.
 */

public class CreditCardRowMapper implements RowMapper {
    @Override
    public BaseBean mapRow(Cursor c) {
        CreditCard card = new CreditCard();
        card.setId(c.getInt(c.getColumnIndex("id")));
        card.setBankName(c.getString(c.getColumnIndex("bankname")));
        card.setCardName(c.getString(c.getColumnIndex("cardname")));
        card.setCardNumber(c.getString(c.getColumnIndex("cardnumber")));
        card.setCvv2(c.getString(c.getColumnIndex("cvv2")));
        card.setIndate(c.getString(c.getColumnIndex("indate")));
        card.setLimit(c.getString(c.getColumnIndex("limits")));
        return card;
    }
}
