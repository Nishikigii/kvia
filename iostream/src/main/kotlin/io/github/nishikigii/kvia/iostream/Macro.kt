package io.github.nishikigii.kvia.iostream

import io.github.nishikigii.kvia.basic.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

val readTasks = CoroutineScope( IO )

inline fun<U> Readable<U>.waitNext( on: CoroutineDispatcher = IO, crossinline action: (Result<U>)->Unit )
{
    readTasks.launch {
        while ( hasNext() ) {
            action( readNext() )
        }
    }
}



val writeTasks = CoroutineScope( IO )

/**
 *
 */
inline fun<U> write( target: Writable<U>, vararg content: U, on: CoroutineDispatcher = IO, crossinline onFailure: (Throwable)->Unit )
{
    writeTasks.launch(on) {
        content.forEach {
            target.appNext(it).onFailure { exception ->
                launch { onFailure(exception) }
            }
        }
    }
}