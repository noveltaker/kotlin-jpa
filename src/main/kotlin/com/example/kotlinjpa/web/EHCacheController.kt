package com.example.kotlinjpa.web

import com.example.kotlinjpa.service.EHCacheService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class EHCacheController constructor(
    private val ehCacheService: EHCacheService
) {

    @GetMapping("num")
    fun getNum(num: Int): Int = ehCacheService.getNum(num)
}
