package fur.token.libfurk

import java.io.BufferedReader
import java.io.PrintWriter
import java.net.Socket


class FurkClient internal constructor(
    private val username: String,
    private val password: String,
    private val server: String = DEFAULT_SERVER,
    private val port: Int = DEFAULT_PORT
) {

    // provides a hook for handling events
    private val eventHandlers = mutableListOf<FurkEventHandler>()

    private val socket: Socket? = null
    val isConnected: Boolean
        get() = socket != null
    private lateinit var reader: BufferedReader
    private lateinit var writer: PrintWriter

    fun addEventHandler(listener: FurkEventHandler) = eventHandlers.add(listener)

    fun removeEventHandler(listener: FurkEventHandler) = eventHandlers.remove(listener)

    fun receiveRawEvent(message: String) {
        val (command, data) = message.split(" ", limit = 2)
        val handleLambda: (FurkEventHandler) -> Unit = when (command) {
            "login" -> { it: FurkEventHandler -> it.loginEvent(data) }
            "sendChat" -> { it: FurkEventHandler -> it.messageReceived(data) }
            else -> { it: FurkEventHandler -> it.otherEvent("$command $data") }
        }
        eventHandlers.forEach(handleLambda);
    }

    fun sendRawCommand(rawCommand: String): Boolean {
        if (!isConnected) {
            println("Lib: Not connected, ignoring command. ($rawCommand)")
            return false
        }
        println("Lib: Would send to server: $rawCommand")
        return true
    }

    fun connect() {
        println("Lib: start connect Hello world from lib")

//        val socket = Socket(server, port)
//        reader = BufferedReader(InputStreamReader(socket.getInputStream()))
//        writer = PrintWriter(socket.getOutputStream(), true)
//
//        Commands.login(username, password)
//
//        runBlocking {
//            launch(Dispatchers.IO) {
//                try {
//                    reader.useLines { lines ->
//                        lines.forEach { response ->
//                            listeners.forEach { listener ->
//                                listener.handle(response)
//                            }
//                        }
//                    }
//                } catch (e: IOException) {
//                    e.printStackTrace()
//                }
//            }
//        }

//        println("> Starting receiver")
//        val receiver = Thread { receiverLoop(socket) }
//        receiver.start()
//
//        val sender = Thread {
//            println("Press enter to send login")
//            readlnOrNull()
//
//            println("> Sending login")
//            out.println("connect xxxx")
//
//            println("Press enter to quit")
//            readlnOrNull()
//            receiverRunning = false
//        }
//        sender.start()
//        sender.join()
//
//        println("> Closing connection")
//        out.println("quit")
//        Thread.sleep(1000L)
//        out.close()
//        socket.close()
        send(FurkCommand.Connect(username, password))
    }

    fun send(command: FurkCommand) {
        command.execute(this)
    }

    override fun toString(): String {
        return "FurkClient(username='$username', password='***', server='$server', port=$port, leventHandlerCount=${eventHandlers.count()}, isConnected=$isConnected)"
    }
}


//package fur.token.libfurk
//
//interface EventListener {
//    fun onChatReceive(user: String, message: String)
//}
//
//enum class ServerEvent(val handle: (Client2, List<String>) -> Unit) {
//    CHATRECIEVE({ client, parts ->
//        if (parts.size == 3) {
//            client.fireChatReceiveEvent(parts[1], parts[2])
//        }
//    });
//    // Other server events...
//}
//
//class GameClient(private val server: String, private val port: Int) {
//    private lateinit var socket: Socket
//    private lateinit var reader: BufferedReader
//    private lateinit var writer: PrintWriter
//    private val listeners = mutableListOf<GameEventListener>()
//
//    fun connect(username: String, password: String): CompletableFuture<Void> {
//        socket = Socket(server, port)
//        reader = BufferedReader(InputStreamReader(socket.getInputStream()))
//        writer = PrintWriter(socket.getOutputStream(), true)
//
//        sendCommand("login $username $password")
//
//        return CompletableFuture.runAsync {
//            try {
//                reader.useLines { lines ->
//                    lines.forEach { response ->
//                        handleResponse(response)
//                    }
//                }
//            } catch (e: IOException) {
//                e.printStackTrace()
//                // Handle the exception
//            }
//        }
//    }
//
//    fun disconnect() {
//        sendCommand("logout")
//        socket.close()
//    }
//
//    fun sendCommand(command: String) {
//        writer.println(command)
//    }
//
//    fun addListener(listener: GameEventListener) {
//        listeners.add(listener)
//    }
//
//    fun removeListener(listener: GameEventListener) {
//        listeners.remove(listener)
//    }
//
//    private fun handleResponse(response: String) {
//        val parts = response.split(" ", limit = 3)
//        try {
//            val event = ServerEvent.valueOf(parts[0])
//            event.handle(this, parts)
//        } catch (e: IllegalArgumentException) {
//            // Handle the case where the response does not correspond to a known server event
//        }
//    }
//
//    fun fireChatReceiveEvent(user: String, message: String) {
//        listeners.forEach { it.onChatReceive(user, message) }
//    }
//}
//
//object AccountManager {
//    private const val SERVER = "game.server.com"
//    private const val PORT = 1234
//
//    fun createClient(username: String, password: String): CompletableFuture<GameClient> {
//        val client = GameClient(SERVER, PORT)
//        return client.connect(username, password).thenApply { client }
//    }
//}
//
//
//
