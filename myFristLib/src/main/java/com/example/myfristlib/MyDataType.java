package com.example.myfristlib;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class MyDataType implements Parcelable {
    public static final Creator<MyDataType> CREATOR = new Creator<MyDataType>() {
        @Override
        public MyDataType createFromParcel(Parcel in) {
            return new MyDataType(in);
        }

        @Override
        public MyDataType[] newArray(int size) {
            return new MyDataType[size];
        }
    };

    private int id = 110;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MyDataType() {

    }


    protected MyDataType(Parcel in) {
        id = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
    }
}
