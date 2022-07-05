package com.example.kotlinjpa.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class Review constructor(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private var id: Long?,
    @Column(nullable = false) private var title: String?,
    @Column(nullable = false) private var content: String?,
    @ManyToOne @JoinColumn(name = "user_id", nullable = false) private var user: User?
) {

    constructor(title: String?, content: String?, user: User?) : this(null, title, content, user)

    fun getTitle() = this.title

    fun getContent() = this.content

    fun getUser() = this.user
}