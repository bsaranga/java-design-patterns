package designpatterns.state_pattern;

public class Main {
    
    // Document Object
    // -- States: Draft, Moderation, Published:
    // Draft: Can be edited, deleted, publish
    // Moderation: Can be reviewed, rejected, or published
    // Published: Can be viewed, can't reject

    public static void main(String[] args) {
        
        // By default, the doc is in Draft state
        Document doc = new Document();
        
        // Publish the document
        doc.publish(); // Draft -> Moderation

        doc.reject(); // Moderation -> Draft

        doc.publish(); // Draft -> Moderation

        doc.publish(); // Moderation -> Published
    }
}
