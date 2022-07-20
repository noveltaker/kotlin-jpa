package com.example.kotlinjpa.service

import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class EHCacheService {

    @Cacheable(value = ["cacheStore"], key = "#number")
    fun getNum(number: Int): Int {
        println("square of $number}")
        return number
    }
}
