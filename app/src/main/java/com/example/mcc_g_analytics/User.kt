package com.example.mcc_g_analytics

import android.os.Parcel
import android.os.Parcelable

class User (var id:Int, var pageName: String?, var time: String? ) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()

    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(pageName)
        parcel.writeString(time)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }

        val COL_ID = "id"
        val COL_NAME = "name"
        val COL_TIME = "time"


        val TABLE_NAME = "Student"
        val TABLE_CREATE = "create table $TABLE_NAME ($COL_ID integer primary key autoincrement," +
                "$COL_NAME text not null, $COL_TIME text not null)"
    }
}