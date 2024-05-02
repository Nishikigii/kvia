package io.github.nishikigii.kvia.fsys.local

import io.github.nishikigii.kvia.basic.Reason
import io.github.nishikigii.kvia.basic.Result
import io.github.nishikigii.kvia.fsys.Item
import io.github.nishikigii.kvia.iostream.Readable
import io.github.nishikigii.kvia.iostream.Writable

/**
 *
 */
sealed class Directory( path: String ): Item<String>(path)
{
    class Folder( path: String ): Directory(path), Readable<File>, Writable<File>
    {
        override fun hasNext(): Boolean
        {
            TODO("Not yet implemented")
        }

        override fun readNext(): Result<File>
        {
            TODO("Not yet implemented")
        }

        override fun appNext(content: File): Reason
        {
            TODO("Not yet implemented")
        }
    }

    class File( path: String ): Directory(path), Readable<Byte>, Writable<Byte>
    {
        override fun hasNext(): Boolean
        {
            TODO("Not yet implemented")
        }

        override fun readNext(): Result<Byte>
        {
            TODO("Not yet implemented")
        }

        override fun appNext(content: Byte): Reason
        {
            TODO("Not yet implemented")
        }
    }

    class Unknown( path: String ): Directory(path)
    {
        fun<T: Directory> ensure( handle: (Throwable)-> T ): T
        {
            TODO()
        }

        fun createFolder(): Reason { TODO() }

        fun createFile(): Reason { TODO() }
    }

}