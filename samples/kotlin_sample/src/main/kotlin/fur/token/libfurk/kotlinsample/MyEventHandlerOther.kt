package fur.token.libfurk.kotlinsample

import fur.token.libfurk.FurkEventHandler

class MyEventHandlerOther : FurkEventHandler {
    override fun loginEvent(welcomeMessage: String) {
        println("HandlerOther: Received welcome message: $welcomeMessage")
    }

    override fun messageReceived(message: String) {
        println("HandlerOther: Received chat message: $message")
    }

    override fun otherEvent(rawData: String) {
        println("HandlerOther: Received other type of even. Content: $rawData")
    }
}
