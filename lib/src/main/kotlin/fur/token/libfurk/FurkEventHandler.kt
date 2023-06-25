package fur.token.libfurk

interface FurkEventHandler {
    fun loginEvent(welcomeMessage: String) {
        // default hook does nothing
    }

    fun messageReceived(message: String) {
        // default hook does nothing
    }

    fun otherEvent(rawData: String) {
        // default hook does nothing
    }
}
