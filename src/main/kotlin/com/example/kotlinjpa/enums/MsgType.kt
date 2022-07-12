package com.example.kotlinjpa.enums

enum class MsgType constructor(
    private val code: String
) {

    //Same Email Exception
    SameEmailError("D001"),

    // system error
    SystemError("E001");

    fun getCode() = this.code
}