package designpatterns.chain_of_resp;

public class TypeBHandler extends Handler {
    
    @Override
    public void handleRequest(Request request) {
        if (request.getRequestType().equals("TypeB")) {
            // logic here....
            System.out.println("TypeBHandler handles request: " + request.getRequestType());
        } else {
            handleNext(request);
        }
    }
}
