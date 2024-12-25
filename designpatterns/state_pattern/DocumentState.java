package designpatterns.state_pattern;

public interface DocumentState {
    public void publish(Document document);
    public void reject(Document document); 
}
