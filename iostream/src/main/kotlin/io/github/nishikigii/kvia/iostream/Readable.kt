package io.github.nishikigii.kvia.iostream

import io.github.nishikigii.kvia.basic.Result

/**
 * output stream from outer sources.
 * Unit -> type of minimize readable unit
 */
interface Readable<Unit>
{

    /**
     * whether next unit exists
     */
    fun hasNext(): Boolean

    /**
     * read next unit
     */
    fun readNext(): Result<Unit>

}