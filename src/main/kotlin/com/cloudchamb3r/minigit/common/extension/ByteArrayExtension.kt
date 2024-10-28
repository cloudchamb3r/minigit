package com.cloudchamb3r.minigit.common.extension

import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody

fun ByteArray.toStreamingResponseBody(): StreamingResponseBody = StreamingResponseBody {
    it.write(this)
}
