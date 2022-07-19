package com.example.kotlinjpa.service

import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class EHCacheService {

    @Cacheable(value = ["cacheStore"], key = "#number")
    fun getNum(number: Int): BigDecimal {
        val square: BigDecimal = (number as BigDecimal).multiply(number as BigDecimal)
        println("square of $number is $square")
        return square
    }
}
