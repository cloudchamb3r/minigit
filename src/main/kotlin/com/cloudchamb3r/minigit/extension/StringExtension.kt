package com.cloudchamb3r.minigit.extension

import org.springframework.beans.factory.annotation.Value
import java.net.URI
import java.nio.file.Paths

fun String.toAbsoluteURI(): URI = URI.create("file://${Paths.get(this).toAbsolutePath()}")

