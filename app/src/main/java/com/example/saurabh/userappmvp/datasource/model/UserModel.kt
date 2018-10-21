package com.example.saurabh.userappmvp.datasource.model

import android.os.Parcel
import android.os.Parcelable
import android.os.SystemClock
import java.util.*

data class User(
        var id : Int? = null,
        var name : String ?= null,
        var gender : Gender ?= null,
        var age : Int? = null,
        var description: String ?= null,
        var updatedTime : String ?= Calendar.getInstance().timeInMillis.toString()
) : BaseResponse(),Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            Gender.valueOf(parcel.readString() ?: Gender.MALE.value()),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString()) {
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(id ?: 0)
        dest?.writeString(name)
        dest?.writeString(gender?.value() ?: Gender.MALE.value())
        dest?.writeInt(age ?: 0)
        dest?.writeString(description)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

}

enum class Gender {
    MALE,
    FEMALE,
    TRANSGENDER,;

    fun value(): String = this.name
}

open class BaseResponse(var status : Int = -1,
        var message: String = "Default Message"
)
