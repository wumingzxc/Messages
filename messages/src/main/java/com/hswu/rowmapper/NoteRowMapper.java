package com.hswu.rowmapper;

import android.database.Cursor;

import com.hswu.bean.BaseBean;
import com.hswu.bean.CreditCard;
import com.hswu.bean.Note;
import com.hswu.util.URIField;

/**
 * Created by HandsomeWu on 2016/7/23.
 */

public class NoteRowMapper implements RowMapper {
    @Override
    public BaseBean mapRow(Cursor c) {
        Note note = new Note();
        note.setId(c.getInt(c.getColumnIndex("id")));
        note.setNoteName(c.getString(c.getColumnIndex(URIField.SAFEREMARK_REMARKTITLE)));
        note.setNoteContent(c.getString(c.getColumnIndex(URIField.SAFEREMARK_REMARKCONTENT)));
        return note;
    }
}
