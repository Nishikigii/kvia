package test.kvia.logging

import io.github.nishikigii.kvia.basic.file
import io.github.nishikigii.kvia.basic.property
import kotlin.text.Charsets.UTF_8

/**
 *
 */
private fun main()
{
    val javaHome = file( property("java.home") )
    val javaExec = file( javaHome, "bin/java" )
    val appDir = file("jar")
    val process = ProcessBuilder()
        .directory(javaHome)
        .command(javaExec.path, "-jar", appDir.path)
        .start()
    process.inputStream.reader( UTF_8 ).buffered(2048).forEachLine {
        println(it)
    }
    val exitCode = process.apply { waitFor() }.exitValue()
    println("process exited with code[$exitCode]")
}