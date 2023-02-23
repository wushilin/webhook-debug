package net.wushilin.webhookdebug;

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import java.io.OutputStream
import java.util.*
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

data class Request(val request: HttpServletRequest) {
    companion object {
        val om = ObjectMapper().registerKotlinModule().writerWithDefaultPrettyPrinter()
    }

    fun dumpTo(os: OutputStream) {
        val inputStream = request.inputStream
        val method = request.method
        val serverPort = request.serverPort
        val serverName = request.serverName
        val URI = request.requestURI
        val URL = request.requestURL
        val queryString = request.queryString
        val remoteAddress = request.remoteAddr
        val remotePort = request.remotePort
        val remoteUser = request.remoteUser
        val remoteHost = request.remoteHost

        val headers = mutableListOf<Header>()
        request.headerNames.asIterator().forEach {
            val headerValues = mutableListOf<String>()
            val key = it
            val values = request.getHeaders(key)
            values?.asIterator()?.forEach { valueSingle ->
                headerValues.add(valueSingle)
            }
            headers.add(Header(key, headerValues))
        }

        val requestRecord = RecordStructure(method, serverName, serverPort, URI, URL.toString(),
            queryString, RemoteInfo(remoteUser, remoteHost, remotePort, remoteAddress), headers, inputStream)

        om.writeValue(os, requestRecord)
        os.flush()
    }
}