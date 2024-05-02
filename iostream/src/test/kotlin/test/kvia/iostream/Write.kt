package test.kvia.iostream

import io.github.nishikigii.kvia.basic.Reason
import io.github.nishikigii.kvia.basic.Reason.Fail
import io.github.nishikigii.kvia.basic.pause
import io.github.nishikigii.kvia.iostream.Writable
import io.github.nishikigii.kvia.iostream.write

/**
 *
 */
private fun main()
{
    write( WritableTest, 'a', 'b', 'c' ) {
        println( it.message )
    }

    pause(500)

}

private object WritableTest: Writable<Char>
{
    override fun appNext( content: Char ): Reason
    {
        return Fail("fail on $content")
    }

}