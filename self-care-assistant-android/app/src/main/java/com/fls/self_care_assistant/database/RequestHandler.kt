package com.fls.self_care_assistant.database

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class RequestHandler {
    var baseUrl: String = "***" //TODO enter address
    var urlResource: String = ""
    var httpMethod: String = ""
    var token: String = ""

    fun execute(callBack: (String) -> Unit, jsonObj: String) {
        val thread = Thread {
            try {
                val urlS = baseUrl + urlResource
                println(urlS)
                val url = URL(urlS)
                httpMethod = httpMethod.toUpperCase(Locale.ROOT)
                val connection =
                    url.openConnection() as HttpURLConnection
                connection.requestMethod = httpMethod
                connection.setRequestProperty("User-Agent", "Temperature")
                connection.setRequestProperty("Accept", "application/json")
                connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
                if (urlResource != "auth" && urlResource != "register") {
                    println("bearer set!")
                    connection.setRequestProperty("Bearer", token)
                    println(connection.getRequestProperty("Authorization"))
                }
                if (httpMethod == "POST") {
                    println(urlResource)
                    println(httpMethod)
                    connection.doOutput = true
                    val os = connection.outputStream
                    os.write(jsonObj.toByteArray(charset("UTF-8")))
                    os.close()
                }
                val code = connection.responseCode
                println(code)
                if (code != 200 && code != 201) {
                    System.err.println("connection failed")
                    println("code: $code")
                    callBack(code.toString())
                } else {
                    val br = BufferedReader(
                        InputStreamReader(connection.inputStream, "utf-8")
                    )
                    val response = StringBuilder()
                    var responseLine: String? = null
                    while (br.readLine()
                            .also { responseLine = it } != null
                    ) { //TODO прочекать этот момент
                        response.append(responseLine!!.trim { it <= ' ' })
                    }
                    callBack(response.toString())
                }
                connection.disconnect()
            } catch (e: IOException) {
                e.printStackTrace()
                callBack("error " + e.message)
            }
        }
        thread.start()
    }
}