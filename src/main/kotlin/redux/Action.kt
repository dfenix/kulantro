package redux

//data class Action<out S>(val type: String, val payload: S)
interface Action {
    val type: String
}