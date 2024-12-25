package designpatterns.state_pattern;

public class DraftState implements DocumentState {
    
    @Override
    public void publish(Document document) {
        // other logic...
        System.out.println("Document is promoted to Moderation state");
        // Promote the document state to moderation state
        document.setState(new ModerationState());
    }

    @Override
    public void reject(Document document) {
        System.out.println("Document is already in draft state, therefore can't be rejected");
    }
}
