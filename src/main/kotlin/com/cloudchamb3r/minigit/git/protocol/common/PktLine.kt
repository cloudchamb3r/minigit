package com.cloudchamb3r.minigit.git.protocol.common


class PktLine(
    private val size: Int,
    private val payload: ByteArray,
) {
    init {
        assert(size in 0..0xffff)
        if (size == 0) assert(payload.isEmpty())
        if (size != 0) assert(size == payload.size + 4)
    }

    companion object {
        fun flushPkt(): PktLine {
            return PktLine(0, byteArrayOf())
        }

        fun fromStringPayload(payload: String): PktLine {
            return PktLine(payload.length + 4, payload.toByteArray())
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun toByteArray(): ByteArray {
        if (size == 0) return "0000".toByteArray()

        val hexFormat = HexFormat {
            upperCase = false
            number {
                removeLeadingZeros = false
                prefix = "0000"
            }
        }

        val hexSize = size.toHexString(hexFormat).toByteArray()
            .takeLast(4) // idk why, but minLength is not supported on NumberHexFormat
            .toByteArray()

        return byteArrayOf(*hexSize, *payload)
    }
}