package io.github.nishikigii.kvia.basic

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * loop scope.
 */
interface Loop
{
    /**
     * break loop.
     */
    fun escape()

}

/**
 * content will be loop executing until escape() called inside.
 * @param millis execution interval: millisecond
 * @param content the block executed in loop
 * the result of the last loop will be returned.
 */
inline fun <T> loop( millis: ULong = 0uL,content: Loop.()->T ): T
{
    var escape = false
    val scope = object: Loop
    {
        override fun escape() {
            escape = true
        }
    }
    val switch = (millis != 0uL)
    val waiting = if ( switch ) millis.toLong() else 0L
    var result = scope.content()
    loop@while (true) {
        if ( escape ) return result
        if ( switch ) pause(waiting)
        result = scope.content()
    }
}

/**
 * let the current thread wait some millis.
 * same as Thread.sleep(...)
 */
@Suppress("NOTHING_TO_INLINE")
inline fun pause( millis: Long ) = Thread.sleep(millis)