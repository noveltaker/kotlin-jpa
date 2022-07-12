package com.example.kotlinjpa.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.PrePersist

@Entity(name = "users")
data class User constructor(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private var id: Long?,
    @Column(nullable = false) private var email: String?,
    @Column(nullable = false) private var password: String?,
    @Column private var point: Int?
) {

    constructor(email: String, password: String) : this(null, email, password, null)

    fun getId() = this.id

    fun getEmail() = this.email

    fun getPassword() = this.password

    @PrePersist
    fun prePersist() {
        // user insert 시 null 이면 0 으로 초기값 세팅
        point.let {
            it ?: 0
        }
    }
}
