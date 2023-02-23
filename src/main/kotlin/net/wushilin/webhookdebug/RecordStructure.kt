package net.wushilin.webhookdebug;

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.io.InputStream

data class RecordStructure(val method:String?, val serverName:String?, val serverPort:Int?,
                           val URI:String?, val URL:String?, val queryString:String?, val remoteInfo:RemoteInfo,
                           val headers:List<Header?>?,
                           @JsonSerialize(using = Base64Serializer::class)
                           val body: InputStream?
)
