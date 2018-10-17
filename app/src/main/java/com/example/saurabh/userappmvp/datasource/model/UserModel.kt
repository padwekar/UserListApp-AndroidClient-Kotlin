package com.example.saurabh.userappmvp.datasource.model

import android.os.Message
import android.os.SystemClock

data class User(
        var id : Int,
        var name : String,
        var gender : Gender = Gender.MALE,
        var age : Int,
        var description: String,
        var updatedTime : String = SystemClock.currentThreadTimeMillis().toString()
) : BaseResponse()

enum class Gender {
    MALE,
    FEMALE,
    TRANSGENDER,;

    fun value(): String = this.name
}

open class BaseResponse(var status : Int = -1,
        var message: String = "Default Message"
)
