package io.github.nishikigii.kvia.iostream

import io.github.nishikigii.kvia.basic.Reason

/**
 *
 */
interface Writable<Unit>
{
    fun appNext( content: Unit ): Reason

}