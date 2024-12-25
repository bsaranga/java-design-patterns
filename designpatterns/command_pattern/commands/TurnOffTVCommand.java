package designpatterns.command_pattern.commands;
import designpatterns.command_pattern.Command;
import designpatterns.command_pattern.Television;

public class TurnOffTVCommand implements Command {
    private Television tv;

    public TurnOffTVCommand(Television tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        this.tv.turnOff();
    }
}
