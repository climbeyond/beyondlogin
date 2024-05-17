package app.climbeyond.beyondlogin.exception

abstract class BeyondLoginException(message: String) : Exception(message) {
    override val message: String
        get() = super.message ?: ""
}