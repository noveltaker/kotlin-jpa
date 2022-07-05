package com.example.kotlinjpa.repository

import com.example.kotlinjpa.domain.Review
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReviewRepository : JpaRepository<Review, Long> {
}