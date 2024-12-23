package repl;

// REPL
// READ, EVALUATE, PRINT, LOOP

public class repl {
    public static void main(String[] args) {
        
        System.out.println("Welcome to the REPL");
        System.out.println("Type 'exit' to exit the REPL");
        
        while (true) {
            System.out.print(">> ");
            String input = System.console().readLine();
            
            if (input.equals("exit")) {
                break;
            }
            
            System.out.println("You typed: " + input);
        }        
    }
}