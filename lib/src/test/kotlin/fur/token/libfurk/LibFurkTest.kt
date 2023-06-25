package fur.token.libfurk

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize

class LibFurkTest : FunSpec({

    test("Lists created clients") {
     LibFurk.listClients() shouldHaveSize 0
     LibFurk.createClient(username = "Test", password = "test", autoConnect = false, server = "localhost");
     LibFurk.listClients() shouldHaveSize 1
    }
})
