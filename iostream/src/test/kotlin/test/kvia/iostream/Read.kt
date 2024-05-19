package test.kvia.iostream

import io.github.nishikigii.kvia.iostream.Item

/**
 *
 */
private fun main()
{
    val file = Item.local("/etc/test")
    val bytes = Array<Byte>( file.size.toInt() ){
        1
    }
    repeat( file.size.toInt() ) { index: Int ->
        bytes[index] = file[index.toULong()].unwrap()
    }
    bytes.forEach {
        print(it.toInt().toChar())
    }
}