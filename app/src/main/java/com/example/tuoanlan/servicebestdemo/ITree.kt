package com.example.tuoanlan.servicebestdemo

import android.os.Parcel
import android.os.Parcelable

class ITree() : Parcelable {
    var name: String? =null

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
    }

    constructor(treeName: String):this(){
        name= treeName
    }





    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
    }

    //自动生成的代码并没有重写这个方法，所以记得加上
    fun readFromParcel(dest: Parcel) {
        name = dest.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ITree> {
        override fun createFromParcel(parcel: Parcel): ITree {
            return ITree(parcel)
        }

        override fun newArray(size: Int): Array<ITree?> {
            return arrayOfNulls(size)
        }
    }


}