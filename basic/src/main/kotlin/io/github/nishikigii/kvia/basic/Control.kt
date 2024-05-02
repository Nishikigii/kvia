package io.github.nishikigii.kvia.basic

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
    var key = true
    val scope = object: Loop
    {
        override fun escape() {
            key = false
        }
    }
    val switch = (millis == 0uL)
    val waiting = millis.toLong()
    var result = scope.content()
    while (key) result = if ( switch ) scope.content() else {
        pause(waiting); scope.content()
    }
    return result
}

/**
 * let the current thread wait some millis.
 * same as Thread.sleep(...)
 */
@Suppress("NOTHING_TO_INLINE")
inline fun pause( millis: Long ) = Thread.sleep(millis)