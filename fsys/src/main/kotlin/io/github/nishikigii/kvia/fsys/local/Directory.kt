package io.github.nishikigii.kvia.fsys.local

import io.github.nishikigii.kvia.fsys.Item

/**
 *
 */
sealed class Directory( path: String ): Item<String>(path)
{

}