package designpatterns.chain_of_resp;

// Types of objects in the chain of responsibility pattern:
// 1. Handler: Interface for handling requests.
// 2. ConcreteHandler: Handles requests it is responsible for.
// 3. Client: Sends requests to the first handler in the chain.
// 4. Request: Represents a request.

public class Main {

    public static void main(String args[]) {
        // Creating the handlers
        Handler typeAHandler = new TypeAHandler();
        Handler typeBHandler = new TypeBHandler();
        Handler typeCHandler = new TypeCHandler();

        // Setting the chain of responsibility
        // typeAHnadler -> typeBHandler
        // A -> B -> C
        typeAHandler.setNextHandler(typeBHandler);
        typeBHandler.setNextHandler(typeCHandler);

        // Creating the request
        Request request = new Request("TypeC");

        typeAHandler.handleRequest(request);
    }
}