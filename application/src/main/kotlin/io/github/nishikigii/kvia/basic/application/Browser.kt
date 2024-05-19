package io.github.nishikigii.kvia.basic.application

import io.github.nishikigii.kvia.basic.Reason
import io.github.nishikigii.kvia.basic.Reason.Fail
import io.github.nishikigii.kvia.basic.Reason.Success
import io.github.nishikigii.kvia.basic.Result
import io.github.nishikigii.kvia.basic.SystemType.Linux
import io.github.nishikigii.kvia.basic.SystemType.MacOS
import io.github.nishikigii.kvia.basic.SystemType.Windows
import io.github.nishikigii.kvia.basic.file
import io.github.nishikigii.kvia.basic.runtime
import io.github.nishikigii.kvia.basic.string
import io.github.nishikigii.kvia.basic.summary
import io.github.nishikigii.kvia.basic.systemType
import io.github.nishikigii.kvia.logging.detail
import io.github.nishikigii.kvia.logging.logging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileNotFoundException
import java.net.URI

interface Browser
{
    /**
     * open uri and returns whether success
     */
    fun open( link: URI ): Reason


    /**
     * Google Chrome browser.
     */
    class Chrome( private val executable: File ): Browser
    {
        companion object
        {
            fun local( path: String ): Result<Chrome>
            {
                val executable = file(path).apply {
                    if ( !exists() )
                        return Result.error( FileNotFoundException("could not found the chrome executable file") )
                    if ( !canExecute() )
                        return Result.error( IllegalArgumentException("given file none-executable") )
                }
                return Result.done( Chrome(executable) )
            }
        }

        @Suppress("NAME_SHADOWING")
        override fun open( link: URI ): Reason
        {
            val working = executable.parentFile
            val executable = executable.path
            val link = link.string
            try
            {
                detail("open URI($link) with google chrome installed at File($executable)")
                val process = ProcessBuilder()
                    .directory(working)
                    .command(executable, link)
                    .start()
                CoroutineScope( Dispatchers.IO ).launch {
                    process.inputStream.bufferedReader().forEachLine { line ->
                        logging(tag = arrayOf("Google Chrome"), message = line)
                    }
                }
            }
            catch ( exception: Throwable )
            {
                return Fail( exception )
            }

            return Success
        }

    }

    companion object
    {
        /**
         * system default browser.
         * it perhaps is none!
         */
        val systemDefault = systemDefault()

        /**
         * open specify uri with system default browser.
         */
        @Suppress("NAME_SHADOWING")
        fun open( link: String ): Reason
        {
            val link = try { URI(link) } catch ( exception: Exception ) {
                val exception = exception.summary {
                    IllegalArgumentException("illegal format to the link")
                }
                return Fail( exception )
            }
            systemDefault.checkout {
                return Fail( it )
            }
            systemDefault.unwrap().open( link )
            return Success
        }

        private fun systemDefault(): Result<Browser>
        {
            return when( systemType ) {
                MacOS -> macOS()
                Linux -> linux()
                Windows -> windows()
                else -> Result.error("default browser does not exist on current system")
            }
        }

        private fun macOS(): Result<Browser>
        {
            val browser = object: Browser {
                override fun open( link: URI ): Reason {
                    runtime.exec("open $link")
                    return Success
                }
            }
            return Result.done(browser)
        }

        private fun linux() = Result.error<Browser>("method does not implemented on linux")

        private fun windows() = Result.error<Browser>("method does not implemented on windows")

    }

}