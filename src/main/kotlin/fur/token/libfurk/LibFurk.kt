package fur.token.libfurk

import java.util.*

const val DEFAULT_SERVER = "lightbringer.furcadia.com"
const val DEFAULT_PORT = 6500

private val gameClients = mutableSetOf<FurkClient>()

object LibFurk {

    @JvmStatic
    fun convertBase(code: String): String {
        // TODO Test function
        return code.lowercase()
    }

    @JvmStatic
    fun createClient(
        username: String,
        password: String,
        eventHandler: FurkEventHandler = object : FurkEventHandler {},
        autoConnect: Boolean = true,
        server: String = DEFAULT_SERVER,
        port: Int = DEFAULT_PORT
    ) = FurkClient(username, password, server, port).apply {
        gameClients.add(this)
        addEventHandler(eventHandler)
        if (autoConnect) connect()
    }

    @JvmStatic
    fun listClients() = Collections.unmodifiableSet(gameClients)
}
