package io.github.nishikigii.kvia.basic.application

/**
 * application.
 */
interface Application
{
    /**
     * application builder name, usually as a domain.
     */
    val group: String

    /**
     * application name.
     */
    val name: String

}