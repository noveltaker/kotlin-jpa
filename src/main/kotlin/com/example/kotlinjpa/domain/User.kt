package com.example.kotlinjpa.domain

import javax.persistence.*

@Entity(name = "users")
data class User constructor(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private var id: Long?,
    @Column(nullable = false) private var email: String?,
    @Column(nullable = false) private var password: String?
) {

    constructor(email: String, password: String) : this(null, email, password)
}