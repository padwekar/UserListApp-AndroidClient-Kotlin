package com.example.saurabh.userappmvp.datasource.model

import android.os.SystemClock
import java.util.*

data class User(
        var id : Int? = null,
        var name : String ?= null,
        var gender : Gender ?= null,
        var age : Int? = null,
        var description: String ?= null,
        var updatedTime : String ?= Calendar.getInstance().timeInMillis.toString()
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
