package com.cloudchamb3r.minigit.git.transfer

import java.io.File

interface GitTransfer {
    fun on(dir: File): GitTransfer

    // dumb protocol
    fun infoRefs(): String
    fun head(): String
    fun objects(commit: String): ByteArray
    fun objectsInfoAlternatives(): String
    fun objectsInfoPacks(): String
    fun objectsInfoPack(packId: String): ByteArray

    // smart protocol
    fun infoReceivePack(): String
    fun infoUploadPack(): String
    fun handleReceivePack(): String
    fun handleUploadPack(): String
}