package com.cloudchamb3r.minigit.git.protocol.common

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class PktLineTest {
    @Test
    fun testFlushPkt() {
        val actual = PktLine.flushPkt().toByteArray()
        val expected = "0000".toByteArray()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun testFromStringPayload() {
        val actualGitReceivePack = PktLine.fromStringPayload("# service=git-receive-pack\n").toByteArray()
        val expectedGitReceivePack = "001f# service=git-receive-pack\n".toByteArray()
        assertThat(actualGitReceivePack).isEqualTo(expectedGitReceivePack)

        val actualGitUploadPack = PktLine.fromStringPayload("# service=git-upload-pack\n").toByteArray()
        val expectedGitUploadPack = "001e# service=git-upload-pack\n".toByteArray()
        assertThat(actualGitUploadPack).isEqualTo(expectedGitUploadPack)
    }

    @Test
    fun throwIfInvalidLength() {
        // negative length
        assertThrows(AssertionError::class.java) {
            PktLine(-1, byteArrayOf())
        }

        // not matched length
        assertThrows(AssertionError::class.java) {
            PktLine(11, "hello world".toByteArray())
        }

        // larger than 0xffff
        assertThrows(AssertionError::class.java) {
            val len = 0x10000
            val value = (0..len-4).map { '0'.code.toByte() }.toByteArray()
            PktLine(len,  value)
        }
    }
}