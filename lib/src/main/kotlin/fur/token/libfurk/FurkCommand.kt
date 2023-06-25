package fur.token.libfurk

sealed class FurkCommand {
    abstract fun execute(client: FurkClient)

    data class Connect(val username: String, val password: String) : FurkCommand() {
        override fun execute(client: FurkClient) {
            if (client.sendRawCommand("login $username $password")) {
                client.receiveRawEvent("login successful $username")
            }
        }
    }

    data class SendMessageToChat(val message: String) : FurkCommand() {
        override fun execute(client: FurkClient) {
            if (client.sendRawCommand("sendChat $message")) {
                client.receiveRawEvent("sendChat successful")
            }
        }
    }

    data class SendMessageToDM(val username: String, val message: String) : FurkCommand() {
        override fun execute(client: FurkClient) {
            if (client.sendRawCommand("sendToDM $username $message")) {
                client.receiveRawEvent("sendToDM successful")
            }
        }
    }
}
