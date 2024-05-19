package io.github.nishikigii.kvia.iostream

import io.github.nishikigii.kvia.basic.Result
import io.github.nishikigii.kvia.basic.Result.Companion.done
import io.github.nishikigii.kvia.basic.file
import java.io.RandomAccessFile

/**
 *
 */
interface Item<index,unit>
{
    operator fun get( index: index ): Result<unit>

    val size: ULong

    companion object
    {
        fun local( path: String ): Item<ULong,Byte>
        {
            return object: Item<ULong,Byte> {
                private val file = file(path)
                override val size = file.length().toULong()
                override fun get(index: ULong): Result<Byte> {
                    if ( !file.exists() )
                        return error("item not found")
                    val reader = RandomAccessFile(file, "r")
                    reader.seek( index.toLong() )
                    return done( reader.readByte() )
                }
            }
        }

    }

}