package fur.token.libfurk.kotlinsample

import fur.token.libfurk.FurkEventHandler

class MyEventHandler : FurkEventHandler {
    override fun loginEvent(welcomeMessage: String) {
        println("Handler: Received welcome message: $welcomeMessage")
    }

    override fun messageReceived(message: String) {
        println("Handler: Received chat message: $message")
    }

    override fun otherEvent(rawData: String) {
        println("Handler: Received other type of even. Content: $rawData")
    }
}
