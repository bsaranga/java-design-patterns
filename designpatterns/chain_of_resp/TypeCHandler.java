package designpatterns.chain_of_resp;

public class TypeCHandler extends Handler {
    
    @Override
    public void handleRequest(Request request) {
        if (request.getRequestType().equals("TypeC")) {
            // logic here....
            System.out.println("TypeCHandler handles request: " + request.getRequestType());
        } else {
            handleNext(request);
        }
    }
}
