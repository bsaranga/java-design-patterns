package designpatterns.chain_of_resp;

public class Request {
    
    private String requestType;

    public Request(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestType() {
        return requestType;
    }
}
