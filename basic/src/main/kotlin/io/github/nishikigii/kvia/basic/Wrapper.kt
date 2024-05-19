package io.github.nishikigii.kvia.basic

import io.github.nishikigii.kvia.basic.Result.Companion.done

/**
 * put values which perhaps be null and the reason caused its be null packaged together.
 */
class Result<Inst> private constructor( val value: Inst?, val description: Any )
{
    companion object
    {
        fun <Inst> done( with: Inst, description: String = "not null value" ) = Result( with, description )

        fun <Inst> error( msg: String ) = Result<Inst>( null, Throwable(msg) )

        fun <Inst> error( exception: Throwable ) = Result<Inst>( null, exception )

        fun <Inst> create( with: Inst?, ifNull: String ): Result<Inst> = if ( with == null ) error(ifNull) else done(with)

        fun <Inst> create( with: Inst?, ifNull: Throwable ): Result<Inst> = if ( with == null ) error(ifNull) else done(with)
    }

    fun defaults( default: (Throwable)-> Inst ): Inst
    {
        if ( value != null ) return value
        return default( description as Throwable )
    }

    inline fun unwrap( action: (Throwable)-> Throwable = {it} ): Inst
    {
        if ( value != null ) return value
        throw action( description as Throwable )
    }

    inline fun checkout( action: (Throwable)->Unit )
    {
        if ( value == null ) action( description as Throwable )
    }

}


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


open class Wrap<K,V>( private val data: MutableMap<K,V> )
{
    operator fun get( key: K ) = Result.create(data[key],"unspecified variant case")
}