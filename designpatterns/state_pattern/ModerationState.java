package designpatterns.state_pattern;

public class ModerationState implements DocumentState {
    
    @Override
    public void publish(Document document) {
        System.out.println("Document is published");
        // Promote the document state to published state
        document.setState(new PublishedState());
    }

    @Override
    public void reject(Document document) {
        System.out.println("Document was made a draft doc");
        document.setState(new DraftState());
    }
}
