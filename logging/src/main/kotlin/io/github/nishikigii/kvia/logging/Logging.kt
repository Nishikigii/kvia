package io.github.nishikigii.kvia.logging

/**
 *
 */
@Suppress("NOTHING_TO_INLINE")
inline fun detail( message: String )
{
    println("[detail] $message")
}

@Suppress("NOTHING_TO_INLINE")
inline fun logging( message: String, vararg tag: String )
{
    val prefix = "[logging]${tag.joinToString { "[$it]" }}"
    println("$prefix $message")
}

