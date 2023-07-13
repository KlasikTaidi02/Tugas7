package com.example.tugas4

import android.os.Parcel
import android.os.Parcelable

data class Detail(val id:String ,val judul: String, val namaPenulis: String, val tahun: String, val kategori: String, val url:String ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id.toString())
        parcel.writeString(judul)
        parcel.writeString(namaPenulis)
        parcel.writeString(tahun.toString())
        parcel.writeString(kategori)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Detail> {
        override fun createFromParcel(parcel: Parcel): Detail {
            return Detail(parcel)
        }

        override fun newArray(size: Int): Array<Detail?> {
            return arrayOfNulls(size)
        }
    }
}