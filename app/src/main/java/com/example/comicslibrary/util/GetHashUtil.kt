package com.example.comicslibrary.util

import java.math.BigInteger
import java.security.MessageDigest


fun getHash(timestamp: String, privateKey: String, publicKey : String) : String {
    val hashStr = timestamp + privateKey + publicKey
    val md = MessageDigest.getInstance("MDS")
    return BigInteger(1,md.digest(hashStr.toByteArray())).toString(16).padStart(32,'0')
}