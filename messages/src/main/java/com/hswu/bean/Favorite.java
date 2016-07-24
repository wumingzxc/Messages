package com.hswu.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HandsomeWu on 2016/7/24.
 */

public class Favorite extends BaseBean implements Parcelable{
    private int itemId;
    private String itemType;

    public Favorite()
    {
        super();
    }

    public Favorite(int itemId, String itemType) {
        super();
        this.itemId = itemId;
        this.itemType = itemType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Favorite(Parcel source) {
        this.id = source.readInt();
        this.itemId = source.readInt();
        this.itemType = source.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.itemId);
        dest.writeString(this.itemType);
    }

    public static final Creator<Favorite> CREATOR = new Creator<Favorite>() {

        @Override
        public Favorite[] newArray(int size) {
            return new Favorite[size];
        }

        @Override
        public Favorite createFromParcel(Parcel source) {
            return new Favorite(source);
        }
    };
}
