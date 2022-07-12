package com.example.kotlinjpa.service.dto

data class MsgDTO(private val code: String, private val message: String?) {

    constructor(code: String) : this(code, null)

    fun getCode() = this.code

    fun getMessage() = this.message
}
