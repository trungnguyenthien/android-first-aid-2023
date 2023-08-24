package vn.nhh.aid.utils

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject
import vn.nhh.aid.shareMainActivity
import java.io.IOException
import java.nio.charset.Charset
import java.util.UUID

fun readJsonFromAssets(context: Context, fileName: String): JSONObject? {
    val json: String?
    try {
        val inputStream = context.assets.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        json = String(buffer, Charset.defaultCharset())
    } catch (e: IOException) {
        e.printStackTrace()
        return null
    }

    return try {
        JSONObject(json)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
fun readJsonArrayFromAssets(context: Context, fileName: String): JSONArray? {
    val json: String?
    try {
        val inputStream = context.assets.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        json = String(buffer, Charset.defaultCharset())
    } catch (e: IOException) {
        e.printStackTrace()
        return null
    }

    return try {
        JSONArray(json)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun JSONObject.getListString(name: String): List<String> {
    val arrayObj = optJSONArray(name) ?: return emptyList()
    return List<String>(arrayObj.length()) { arrayObj.getString(it) }
}

fun JSONObject.toList(key: String): List<JSONObject> {
    val list = mutableListOf<JSONObject>()
    val array = optJSONArray(key) ?: JSONArray()
    for (i in 0 until array.length()) {
        val jsonObject = array.optJSONObject(i)
        if (jsonObject != null) {
            list.add(jsonObject)
        }
    }
    return list
}

fun randUUID() = UUID.randomUUID().toString()