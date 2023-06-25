package fur.token.libfurk.kotlinsample

import fur.token.libfurk.FurkCommand
import fur.token.libfurk.LibFurk

fun main(args: Array<String>) {
    // Create a client
    val client = LibFurk.createClient("ExampleUser", args[0], MyEventHandler())

    // We can add our own handler when creating the client or add one later
    //client.addEventHandler(MyEventHandlerOther())

    // If we disable autoConnect and want to manually connect
    //client.connect()

    client.send(FurkCommand.SendMessageToChat("Hello everyone"))
    client.send(FurkCommand.SendMessageToDM("ExampleUser", "Hey!"))

    println("Sample: Known clients: ${LibFurk.listClients()}")
}
