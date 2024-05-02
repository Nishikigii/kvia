package io.github.nishikigii.kvia.basic.application

import io.github.nishikigii.kvia.basic.Result
import io.github.nishikigii.kvia.basic.file
import java.io.File

/**
 * java runtime.
 */
data class Java (

    val version: String,

    val modules: List<String>,

    val architecture: String,

    val os: String,

    val source: String,

    override val executable: String,

    override val group: String,

    override val name: String

): Executable
{
    companion object
    {
        /**
         * parse the build metadata file in the java home folder to create the instance.
         * home dir, executable file, build metadata file are both available.
         */
        @Suppress("NAME_SHADOWING")
        fun from( dir: String ): Result<Java>
        {
            var dir = File(dir)
            // macOS special treatment
            file(dir, "Contents/Home/release").apply {
                if ( exists() ) return Result.done(Companion.resolve(this.readText()))
            }
            // finding the metadata file in foreach upper folders
            var release = file(dir, "release")
            var repeat = 0
            while ( !release.exists() ) {
                if (repeat == 4) return Result.error("java home folder could not be found")
                dir = file(dir.parent)
                release = file(dir, "release")
                repeat ++
            }
            return Result.done( resolve(release.readText()) )
        }

        fun current(): Java
        {
            return Java (
                version = System.getProperty("java.version"),
                modules = listOf(),
                architecture = System.getProperty("os.arch"),
                os = System.getProperty("os.name"),
                source = "unknown",
                executable = "unknown",
                group = "unknown",
                name = "java"
            )
        }

        /**
         * resolve the java build metadata string
         */
        private fun resolve( str: String ): Java
        {
            TODO()
        }
    }

}