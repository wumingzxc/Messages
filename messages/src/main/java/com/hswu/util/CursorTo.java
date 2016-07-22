package com.hswu.util;

import java.util.ArrayList;
import java.util.List;

import com.hswu.bean.BaseBean;
import com.hswu.bean.CreditCard;
import com.hswu.rowmapper.RowMapper;

import android.database.Cursor;

public class CursorTo {

	public static List<? extends BaseBean> cursorToBaseBean(Cursor c, RowMapper rowMapper) {
		List<BaseBean> cards = new ArrayList<BaseBean>();
		if (c != null) {
			while (c.moveToNext()) {
				cards.add(rowMapper.mapRow(c));
			}
		}
		c.close();
		return cards;
	}

}
