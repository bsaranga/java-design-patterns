package designpatterns;

public class hello {
    public static void main(String[] args) {
        SelfReturningClass selfReturningClass = new SelfReturningClass("Hello");
        selfReturningClass.getSelf().setName("ellloooooooo....");
        System.out.println(selfReturningClass.getName());
    }
}

class SelfReturningClass {
    
    private String name;

    public SelfReturningClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public SelfReturningClass getSelf() {
        return this;
    }
}