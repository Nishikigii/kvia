package io.github.nishikigii.kvia.basic

/**
 * save and summary current error.
 */
inline fun Throwable.summary( handle: (Throwable)-> Throwable ): Throwable
{
    return handle(this)
}