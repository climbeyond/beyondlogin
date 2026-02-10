package app.climbeyond.beyondlogin.helpers

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow


expect fun getThread(): String

object BLLogger {
    var tag: String = "BeyondLogin"

    enum class LEVEL(val level: Int) {
        VERBOSE(0),
        DEBUG(1),
        INFO(2),
        WARNING(3),
        ERROR(4),
    }

    class LoggerMessage(val level: LEVEL, val tag: String, val message: String, val thread: String)

    private val logger = MutableSharedFlow<LoggerMessage>(
            replay = 0,
            extraBufferCapacity = 2,
            onBufferOverflow = BufferOverflow.SUSPEND)

    val event : SharedFlow<LoggerMessage> = logger.asSharedFlow()

    fun logVerbose(message: String) {
        logger.tryEmit(LoggerMessage(LEVEL.VERBOSE, tag, message, getThread()))
    }

    fun logDebug(message: String) {
        logger.tryEmit(LoggerMessage(LEVEL.DEBUG, tag, message, getThread()))
    }

    fun logInfo(message: String) {
        logger.tryEmit(LoggerMessage(LEVEL.INFO, tag, message, getThread()))
    }

    fun logWarning(message: String) {
        logger.tryEmit(LoggerMessage(LEVEL.WARNING, tag, message, getThread()))
    }

    fun logError(message: String) {
        logger.tryEmit(LoggerMessage(LEVEL.ERROR, tag, message, getThread()))
    }
}