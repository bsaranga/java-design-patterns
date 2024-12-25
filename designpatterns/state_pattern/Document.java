package designpatterns.state_pattern;

public class Document {
    private DocumentState state;

    public Document() {
        // set default state
        state = new DraftState();
    }

    public void publish() {
        state.publish(this);
    }

    public void reject() {
        state.reject(this);
    }

    public void setState(DocumentState state) {
        this.state = state;
    }
}
