package io.github.nishikigii.kvia.basic

class Result<Inst> private constructor( val value: Inst?, val description: Any )
{
    companion object
    {
        fun <Inst> done( with: Inst, description: String = "not null value" ) = Result( with, description )

        fun <Inst> error( msg: String ) = Result<Inst>( null, Throwable(msg) )

        fun <Inst> error( exception: Throwable ) = Result<Inst>( null, exception )
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

}

@Suppress("NOTHING_TO_INLINE")
inline fun<Inst> Result<Inst>.catch(): Throwable?
{
    return if ( value == null ) description as Throwable else null
}
