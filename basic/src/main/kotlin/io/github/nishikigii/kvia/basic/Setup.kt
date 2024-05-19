package io.github.nishikigii.kvia.basic

import java.io.File

/**
 * file creating
 */
@Suppress("NOTHING_TO_INLINE")
inline fun file( path: String ) = File( path )

/**
 * file creating with child
 */
@Suppress("NOTHING_TO_INLINE")
inline fun file( file: File, child: String ) = File( file, child )

/**
 * convert any objects into string.
 */
val Any.string: String inline get() = this.toString()

/**
 * read jvm property.
 */
@Suppress("NOTHING_TO_INLINE")
inline fun property( key: String ): String = System.getProperty(key)

/**
 * current user home directory.
 */
val userHome = property("user.home")

/**
 * java.lang.Runtime
 */
val runtime inline get() = Runtime.getRuntime()