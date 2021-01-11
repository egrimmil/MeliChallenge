package co.com.challenge.presentation.state

/**
 * Created by Elkin Fracica on 1/11/21.
 */
sealed class State {

    object Loading : State()
    object Empty : State()

    data class Failed(val failure: Throwable) : State()
    data class Success(var data: Any) : State() {
        inline fun <reified T> responseTo() = data as T
    }
}