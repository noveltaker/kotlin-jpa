package com.example.kotlinjpa.mock

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.core.io.ClassPathResource
import java.io.File
import java.nio.file.Files

class MockUtil {

    companion object {

        fun <T : Any> readJsonFileToClass(jsonFileName: String, ofClass: Class<T>): T? {

            val resource: File = ClassPathResource(jsonFileName).file

            val text = String(Files.readAllBytes(resource.toPath()))

            val objectMapper = ObjectMapper()

            return objectMapper.readValue(text, ofClass)
        }
    }
}
