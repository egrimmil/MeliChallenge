package co.com.challenge.network

/**
 * Created by Elkin Fracica on 1/11/21.
 */
sealed class Callback<out L, out R> {

    data class onFailed<out L>(val a: L) : Callback<L, Nothing>()
    data class onSuccess<out R>(val b: R) : Callback<Nothing, R>()

    fun callback(funL: (L) -> Any, funR: (R) -> Any): Any = when (this) {
        is onFailed -> funL(a)
        is onSuccess -> funR(b)
    }

}
