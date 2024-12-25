package designpatterns.command_pattern.commands;
import designpatterns.command_pattern.Command;
import designpatterns.command_pattern.Television;

public class TurnOnTVCommand implements Command {
    private Television tv;

    public TurnOnTVCommand(Television tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        this.tv.turnOn();
    }
}
