package com.hswu.rowmapper;

import android.database.Cursor;

import com.hswu.bean.BaseBean;
import com.hswu.bean.Favorite;
import com.hswu.bean.Note;
import com.hswu.util.URIField;

/**
 * Created by HandsomeWu on 2016/7/23.
 */

public class FavoriteRowMapper implements RowMapper {
    @Override
    public BaseBean mapRow(Cursor c) {
        Favorite favorite = new Favorite();
        favorite.setId(c.getInt(c.getColumnIndex("id")));
        favorite.setItemId(c.getInt(c.getColumnIndex(URIField.FAVORITE_ITEMID)));
        favorite.setItemType(c.getString(c.getColumnIndex(URIField.FAVORITE_ITEMTYPE)));
        return favorite;
    }
}
