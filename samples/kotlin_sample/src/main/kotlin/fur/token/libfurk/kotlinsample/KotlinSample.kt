package fur.token.libfurk.kotlinsample

import fur.token.libfurk.FurkCommand
import fur.token.libfurk.LibFurk

fun main(args: Array<String>) {
    val (username, password) = when (args.size) {
        2 -> args[0] to args[1]
        else -> {
            println("Usage: ./app <username> <password>\n # Using 'example:example' instead.")
            "example" to "example"
        }
    }

    // Create a client
    val client = LibFurk.createClient(username, password, MyEventHandler())

    // We can add our own handler when creating the client or add one later
    //client.addEventHandler(MyEventHandlerOther())

    // If we disable autoConnect and want to manually connect
    //client.connect()

    client.send(FurkCommand.SendMessageToChat("Hello everyone"))
    client.send(FurkCommand.SendMessageToDM("ExampleUser", "Hey!"))

    println("Sample: Known clients: ${LibFurk.listClients()}")
}
