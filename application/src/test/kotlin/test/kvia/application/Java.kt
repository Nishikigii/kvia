package test.kvia.application

import io.github.nishikigii.kvia.basic.application.Java
import io.github.nishikigii.kvia.basic.loop

private fun main(): Unit = loop {

    print("PATH > ")
    val input = readlnOrNull()

    Java.from( input ?: "" )

}