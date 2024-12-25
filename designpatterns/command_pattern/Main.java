package designpatterns.command_pattern;

import designpatterns.command_pattern.commands.TurnOffTVCommand;
import designpatterns.command_pattern.commands.TurnOnTVCommand;

// Receiver = TV
// Command = ON, OFF, VOLUME_UP, VOLUME_DOWN
// CommandInvoker = RemoteControl

public class Main {
    public static void main(String[] args) {
        Television tv = new Television();

        Command turnOn = new TurnOnTVCommand(tv);
        Command turnOff = new TurnOffTVCommand(tv);

        Remote remote = new Remote();
        remote.setCommand(turnOn);
        remote.pressButton();

        remote.setCommand(turnOff);
        remote.pressButton();
    }
}