package io.github.nishikigii.kvia.basic.application

import io.github.nishikigii.kvia.basic.Result
import java.net.URI

/**
 *
 */
class Maven
{
    class Item( override val group: String, override val name: String, val version: String ): Application

    abstract class Repository private constructor()
    {

        abstract fun pull( item: Item ): Result<URI>

        companion object
        {
            fun remote(): Repository
            {
                TODO()
            }

            fun local(): Repository
            {
                TODO()
            }
        }


    }

}