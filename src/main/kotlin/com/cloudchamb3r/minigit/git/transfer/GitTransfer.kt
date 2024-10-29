package com.cloudchamb3r.minigit.git.transfer

import java.io.File
import java.io.OutputStream

interface GitTransfer {
    fun on(dir: File): GitTransfer

    // dumb protocol
    fun infoRefs(): Sequence<ByteArray>
    fun head(): String
    fun objects(commit: String): ByteArray
    fun objectsInfoAlternatives(): String
    fun objectsInfoPacks(): String
    fun objectsInfoPack(packId: String): ByteArray

    // smart protocol
    fun infoReceivePack(): Sequence<ByteArray>
    fun infoUploadPack(): Sequence<ByteArray>
    fun handleReceivePack(): String
    fun handleUploadPack(): String
}