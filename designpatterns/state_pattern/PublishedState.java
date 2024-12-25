package designpatterns.state_pattern;

public class PublishedState implements DocumentState {
    
    @Override
    public void publish(Document document) {
        System.out.println("Document is already published");
    }

    @Override
    public void reject(Document document) {
        System.out.println("Once published, you can't reject the document");
    }
}
