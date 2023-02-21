package net.wushilin.webhookdebug;

import jakarta.servlet.http.HttpServletRequest
import java.io.OutputStream
import java.util.*

data class Request(val request: HttpServletRequest) {
    companion object {
        val encoder = Base64.getMimeEncoder();
    }

    fun dumpTo(os: OutputStream) {
        val inputStream = request.inputStream
        os.write("METHOD: ${request.method}\n".toByteArray())
        os.write("HOST: ${request.serverName}:${request.serverPort}\n".toByteArray())
        os.write("URL: ${request.requestURL}\n".toByteArray())
        os.write("URI: ${request.requestURI}\n".toByteArray())
        os.write("QUERY_STRING: ${request.queryString}\n".toByteArray())
        os.write("REMOTE_ADDR: ${request.remoteAddr}\n".toByteArray())
        os.write("REMOTE_USER: ${request.remoteUser}\n".toByteArray())
        os.write("REMOTE_HOST: ${request.remoteHost}\n".toByteArray())
        os.write("REMOTE_PORT: ${request.remotePort}\n".toByteArray())

        request.headerNames.asIterator().forEach {
            val key = it
            val values = request.getHeaders(key)
            values?.asIterator()?.forEach { valueSingle ->
                os.write("HEADER: ${key} = [${valueSingle}]\n".toByteArray())
            }
        }
        os.write("BODY(BASE64-ENCODED):\n".toByteArray())
        os.flush();
        val b64Stream = encoder.wrap(os)
        inputStream.copyTo(b64Stream)
        b64Stream.flush()
        b64Stream.close()
    }
}