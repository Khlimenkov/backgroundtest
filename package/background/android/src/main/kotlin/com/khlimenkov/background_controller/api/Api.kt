// Autogenerated from Pigeon (v9.1.1), do not edit directly.
// See also: https://pub.dev/packages/pigeon

package com.khlimenkov.background_controller.api

import android.util.Log
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MessageCodec
import io.flutter.plugin.common.StandardMessageCodec
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer

private fun wrapResult(result: Any?): List<Any?> {
  return listOf(result)
}

private fun wrapError(exception: Throwable): List<Any?> {
  if (exception is FlutterError) {
    return listOf(
      exception.code,
      exception.message,
      exception.details
    )
  } else {
    return listOf(
      exception.javaClass.simpleName,
      exception.toString(),
      "Cause: " + exception.cause + ", Stacktrace: " + Log.getStackTraceString(exception)
    )
  }
}

/**
 * Error class for passing custom error details to Flutter via a thrown PlatformException.
 * @property code The error code.
 * @property message The error message.
 * @property details The error details. Must be a datatype supported by the api codec.
 */
class FlutterError (
  val code: String,
  override val message: String? = null,
  val details: Any? = null
) : Throwable()

/**
 * Contains a boolean value.
 *
 * Generated class from Pigeon that represents data sent in messages.
 */
data class BooleanValue (
  val value: Boolean? = null

) {
  companion object {
    @Suppress("UNCHECKED_CAST")
    fun fromList(list: List<Any?>): BooleanValue {
      val value = list[0] as Boolean?
      return BooleanValue(value)
    }
  }
  fun toList(): List<Any?> {
    return listOf<Any?>(
      value,
    )
  }
}

/**
 * Payload for the message that will be sent to the native code
 * and spawn the background service.
 *
 * Generated class from Pigeon that represents data sent in messages.
 */
data class OpenMessage (
  val entryPointRawHandler: Long? = null

) {
  companion object {
    @Suppress("UNCHECKED_CAST")
    fun fromList(list: List<Any?>): OpenMessage {
      val entryPointRawHandler = list[0].let { if (it is Int) it.toLong() else it as Long? }
      return OpenMessage(entryPointRawHandler)
    }
  }
  fun toList(): List<Any?> {
    return listOf<Any?>(
      entryPointRawHandler,
    )
  }
}

@Suppress("UNCHECKED_CAST")
private object ApiFromDartCodec : StandardMessageCodec() {
  override fun readValueOfType(type: Byte, buffer: ByteBuffer): Any? {
    return when (type) {
      128.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          BooleanValue.fromList(it)
        }
      }
      129.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          OpenMessage.fromList(it)
        }
      }
      else -> super.readValueOfType(type, buffer)
    }
  }
  override fun writeValue(stream: ByteArrayOutputStream, value: Any?)   {
    when (value) {
      is BooleanValue -> {
        stream.write(128)
        writeValue(stream, value.toList())
      }
      is OpenMessage -> {
        stream.write(129)
        writeValue(stream, value.toList())
      }
      else -> super.writeValue(stream, value)
    }
  }
}

/**
 * Message class for communication with the native code
 * and the background service.
 *
 * This class is used by the Dart code to send messages to the native code.
 *
 * Generated interface from Pigeon that represents a handler of messages from Flutter.
 */
interface ApiFromDart {
  /** Open the background service. */
  fun open(openMessage: OpenMessage, callback: (Result<Unit>) -> Unit)
  /** Check if the background service is open. */
  fun isOpen(): BooleanValue
  /** Close the background service. */
  fun close(callback: (Result<Unit>) -> Unit)

  companion object {
    /** The codec used by ApiFromDart. */
    val codec: MessageCodec<Any?> by lazy {
      ApiFromDartCodec
    }
    /** Sets up an instance of `ApiFromDart` to handle messages through the `binaryMessenger`. */
    @Suppress("UNCHECKED_CAST")
    fun setUp(binaryMessenger: BinaryMessenger, api: ApiFromDart?) {
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.ApiFromDart.open", codec)
        if (api != null) {
          channel.setMessageHandler { message, reply ->
            val args = message as List<Any?>
            val openMessageArg = args[0] as OpenMessage
            api.open(openMessageArg) { result: Result<Unit> ->
              val error = result.exceptionOrNull()
              if (error != null) {
                reply.reply(wrapError(error))
              } else {
                reply.reply(wrapResult(null))
              }
            }
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.ApiFromDart.isOpen", codec)
        if (api != null) {
          channel.setMessageHandler { _, reply ->
            var wrapped: List<Any?>
            try {
              wrapped = listOf<Any?>(api.isOpen())
            } catch (exception: Throwable) {
              wrapped = wrapError(exception)
            }
            reply.reply(wrapped)
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.ApiFromDart.close", codec)
        if (api != null) {
          channel.setMessageHandler { _, reply ->
            api.close() { result: Result<Unit> ->
              val error = result.exceptionOrNull()
              if (error != null) {
                reply.reply(wrapError(error))
              } else {
                reply.reply(wrapResult(null))
              }
            }
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
    }
  }
}
/**
 * Message class for communication with the native code
 * and the background service.
 *
 * This class is used by the native code to send messages to the Dart code.
 *
 * Generated class from Pigeon that represents Flutter messages that can be called from Kotlin.
 */
@Suppress("UNCHECKED_CAST")
class ApiToDart(private val binaryMessenger: BinaryMessenger) {
  companion object {
    /** The codec used by ApiToDart. */
    val codec: MessageCodec<Any?> by lazy {
      StandardMessageCodec()
    }
  }
  /** Called when the background service is opened. */
  fun afterOpening(callback: () -> Unit) {
    val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.ApiToDart.afterOpening", codec)
    channel.send(null) {
      callback()
    }
  }
  /** Called when the background service is closed. */
  fun afterClosing(callback: () -> Unit) {
    val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.ApiToDart.afterClosing", codec)
    channel.send(null) {
      callback()
    }
  }
}
