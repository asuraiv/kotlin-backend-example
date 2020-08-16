package com.asuraiv.kotlinbackend.example.domain.common.util

fun commaDelimitedListToStringArray(str: String): Array<String> {

    val strList = str.split(",")

    val strArray = Array(strList.size) { "" }
    for(i in strArray.indices) {
        strArray[i] = strList[i]
    }
    return strArray
}

