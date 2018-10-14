package com.example.saurabh.userappmvp.datasource.model

import android.os.SystemClock

data class User(
        var id : Int,
        var name : String,
        var gender : Gender = Gender.MALE,
        var age : Int,
        var description: String,
        var updatedTime : String = SystemClock.currentThreadTimeMillis().toString()
)

enum class Gender {
    MALE,
    FEMALE,
    TRANSGENDER,
}
