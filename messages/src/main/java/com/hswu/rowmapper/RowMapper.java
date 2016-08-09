package com.hswu.rowmapper;

import android.database.Cursor;

import com.hswu.bean.BaseBean;

/**
 * Created by HandsomeWu on 2016/7/23.
 */

public interface RowMapper {
    public BaseBean mapRow(Cursor c);
}
