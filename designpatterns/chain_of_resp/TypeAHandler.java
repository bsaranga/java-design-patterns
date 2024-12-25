package designpatterns.chain_of_resp;

public class TypeAHandler extends Handler {
    
    @Override
    public void handleRequest(Request request) {
        if (request.getRequestType().equals("TypeA")) {
            System.out.println("TypeAHandler handles request: " + request.getRequestType());
        } else {
            handleNext(request);
        }
    }
    
}
