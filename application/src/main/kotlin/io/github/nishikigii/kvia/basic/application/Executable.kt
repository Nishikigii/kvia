package io.github.nishikigii.kvia.basic.application

/**
 * executable application.
 */
interface Executable: Application
{
    /**
     * path to executable.
     */
    val executable: String

    /**
     * application life.
     */
    interface Life
    {
        /**
         * end the life.
         */
        fun exit( code: Int = 0 )

        /**
         * forced end the life.
         */
        fun panic()
    }

}