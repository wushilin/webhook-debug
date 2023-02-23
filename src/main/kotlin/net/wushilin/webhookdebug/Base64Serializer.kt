package net.wushilin.webhookdebug;

import com.fasterxml.jackson.core.Base64Variants
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.io.InputStream

class Base64Serializer:JsonSerializer<InputStream>(){
    override fun serialize(value: InputStream, jgen: JsonGenerator?, serializers: SerializerProvider?) {
        jgen?.writeBinary(Base64Variants.getDefaultVariant(), value, -1)
    }
}
