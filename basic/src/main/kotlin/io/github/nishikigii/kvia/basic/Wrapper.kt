package io.github.nishikigii.kvia.basic

/**
 * wrappers instance with extra information together.
 */
open class Wrapper<Info,Inst>( val instance: Inst, val extra: Map<String,Info> )

/**
 * quick creating wrapper with function
 */
@Suppress("NOTHING_TO_INLINE")
inline fun<Info,Inst> wrap( inst: Inst, vararg extras: Pair<String,Info> ) = Wrapper( inst, extras.toMap() )

/**
 * area with start and end index.
 */
class Area<Index>( val start: Index, val end: Index )

@Suppress("NOTHING_TO_INLINE")
inline infix fun<I> I.`~`( end: I ) = Area( this, end )

/**
 * variants express whether actions fail and brings its reason if failed.
 */
sealed class Reason
{
    class Fail( val reason: Throwable ): Reason()
    {
        constructor( reason: String ): this(Throwable(reason))
    }

    data object Success: Reason()

    /**
     * run block if the variant is fail
     */
    fun onFailure( action: (Throwable)-> Unit )
    {
        when( this ) {
            is Fail -> action(reason)
            else -> return
        }
    }

}