package com.cloudchamb3r.minigit.git.transfer

import java.io.File
import java.io.OutputStream

interface GitTransfer {
    fun on(dir: File): GitTransfer

    // dumb protocol
    fun infoRefs(): ByteArray
    fun head(): String
    fun objects(commit: String): ByteArray
    fun objectsInfoAlternatives(): String
    fun objectsInfoPacks(): String
    fun objectsInfoPack(packId: String): ByteArray

    // smart protocol
    fun infoReceivePack(): ByteArray
    fun infoUploadPack(): ByteArray
    fun handleReceivePack(): String
    fun handleUploadPack(): String
}