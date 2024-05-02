import io.github.nishikigii.kvia.basic.Result
import io.github.nishikigii.kvia.basic.catch
import java.io.IOException
import java.time.LocalTime
import kotlin.random.Random

/**
 *
 */
private fun main()
{
    val result = function()
    val string = result.unwrap {
        when ( it ) {
            is IOException -> { println("exp is io") ;return }
            is NullPointerException -> { println("exp is nup"); return }
            else -> { println("could not tell the exp type"); Exception() }
        }
    }
    println( string )
}

private fun function(): Result<String>
{
    val cause = Random( LocalTime.now().second ).nextBoolean().run {
        if ( this ) IOException() else IllegalStateException()
    }
    return Result.error( cause )
}