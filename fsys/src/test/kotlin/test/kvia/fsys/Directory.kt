package test.kvia.fsys

import io.github.nishikigii.kvia.fsys.local.Directory.Folder
import io.github.nishikigii.kvia.fsys.local.Directory.Unknown

/**
 *
 */
private fun main()
{
    val dir =  Unknown("path").ensure {
        Folder("")
    }
}