package org.opcode.service.command;

import org.opcode.constants.Command;
import org.opcode.exceptions.CommandNotFoundException;
import org.opcode.model.RegisterState;
import org.opcode.service.command.impl.*;

import java.util.Map;

public class CommandFactory {

    // TODO: Command should register themselves.
    // private Map<String, CommandExecutor> commandExecutorMap;

    private final CommandExecutor addConstantCommand;
    private final CommandExecutor addRegisterCommand;
    private final CommandExecutor decrementCommand;
    private final CommandExecutor incrementCommand;
    private final CommandExecutor moveCommand;
    private final CommandExecutor resetCommand;
    private final CommandExecutor setCommand;

    public CommandFactory(RegisterState registerState){
        this.addConstantCommand = new AddConstantCommand(registerState);
        this.addRegisterCommand = new AddRegisterCommand(registerState);
        this.decrementCommand = new DecrementCommand(registerState);
        this.incrementCommand = new IncrementCommand(registerState);
        this.moveCommand = new MoveCommand(registerState);
        this.resetCommand = new ResetCommand(registerState);
        this.setCommand = new SetCommand(registerState);
    }

    public CommandExecutor getCommandExecutor(String input){
        Command command = Command.valueOf(input);
        switch(input){
            case "SET":
                return setCommand;
            case "ADR":
                return addRegisterCommand;
            case "ADD":
                return addConstantCommand;
            case "MOV":
                return moveCommand;
            case "INR":
                return incrementCommand;
            case "DCR":
                return decrementCommand;
            case "RST":
                return resetCommand;
            default:
                throw new CommandNotFoundException("Command not found for input: " + command);

        }
    }
}
