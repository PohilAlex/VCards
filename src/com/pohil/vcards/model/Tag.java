package com.pohil.vcards.model;


import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import com.pohil.vcards.dao.TagDao;
import com.pohil.vcards.dao.WordDao;

public class Tag implements Parcelable {

    public String uid;
    public String name;

    public Tag(Cursor cursor) {
        uid = cursor.getString(cursor.getColumnIndex(TagDao.COLUMN_UID));
        name = cursor.getString(cursor.getColumnIndex(WordDao.COLUMN_NAME));
    }

    public static Parcelable.Creator<Tag> CREATOR = new Parcelable.Creator<Tag>() {

        public Tag createFromParcel(Parcel source) {
            return new Tag(source);
        }

        public Tag[] newArray(int size) {
            return new Tag[size];
        }
    };

    public Tag(Parcel in) {
        uid = in.readString();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(uid);
        parcel.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
