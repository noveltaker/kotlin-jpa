package com.example.kotlinjpa.config

import org.ehcache.event.CacheEvent
import org.ehcache.event.CacheEventListener

class EHCacheEventLogger : CacheEventListener<Any, Any> {

    override fun onEvent(event: CacheEvent<out Any, out Any>?) {
        println(
            "EHCache Event Logger message getKey: ${event!!.key}, getOldValue: ${event!!.oldValue} getNewValue: ${event!!.newValue}"
        )
    }
}
