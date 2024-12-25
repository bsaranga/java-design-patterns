package designpatterns.chain_of_resp;

public abstract class Handler {
    
    private Handler nextHandler;

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(Request request);

    public void handleNext(Request request) {
        if (nextHandler != null) {
            nextHandler.handleRequest(request);
        } else {
            System.out.println("End of chain reached.");
        }
    }
}
