package com.brilliatnbrains.markchat.data.error

import org.json.JSONException
import org.json.JSONObject

object ErrorUtils {

    @Throws(JSONException::class)
    fun convertError(json: String): String {
        return if (json.contains("error_description")) {
            val jsonObj = JSONObject(json)
            jsonObj.getString("error_description")
        } else {
            json
        }
    }
}