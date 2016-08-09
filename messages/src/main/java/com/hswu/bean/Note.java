package com.hswu.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HandsomeWu on 2016/7/24.
 */

public class Note extends BaseBean implements Parcelable{
    private String noteName;
    private String noteContent;

    public Note()
    {
        super();
    }

    public Note(String noteName,String noteContent) {
        super();
        this.noteName = noteName;
        this.noteContent = noteContent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public String getNoteName() {
        return noteName;
    }

    public Note(Parcel source) {
        this.id = source.readInt();
        this.noteName = source.readString();
        this.noteContent = source.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.noteName);
        dest.writeString(this.noteContent);
    }

    public static final Parcelable.Creator<Note> CREATOR = new Creator<Note>() {

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }

        @Override
        public Note createFromParcel(Parcel source) {
            return new Note(source);
        }
    };
}
