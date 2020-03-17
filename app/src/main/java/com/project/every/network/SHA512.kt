package com.project.every.network

import java.math.BigInteger
import java.security.MessageDigest

class SHA512 {

    companion object{
        fun getSH512(input : String) : String? {
            var toReturn: String? = null
            try {
                val digest = MessageDigest.getInstance("SHA-512")
                digest.reset()
                digest.update(input.toByteArray(charset("utf8")))
                toReturn = String.format("%0128x", BigInteger(1, digest.digest()))
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return toReturn
        }
    }
}