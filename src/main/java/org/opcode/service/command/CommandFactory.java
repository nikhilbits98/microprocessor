package org.opcode.service.command;

import org.opcode.constants.Command;
import org.opcode.exceptions.CommandNotFoundException;
import org.opcode.repository.IRegisterState;
import org.opcode.repository.impl.InMemoryRegisterState;
import org.opcode.service.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private final Map<Command, CommandExecutor> commandExecutorMap;
    // List<CommandExecutor> allCommandExecutors;

    public CommandFactory(IRegisterState registerState){
        this.commandExecutorMap = new HashMap<Command, CommandExecutor>();
        this.registerCommandExecutor(new AddConstantCommand(registerState));
        this.registerCommandExecutor(new AddRegisterCommand(registerState));
        this.registerCommandExecutor(new DecrementCommand(registerState));
        this.registerCommandExecutor(new IncrementCommand(registerState));
        this.registerCommandExecutor(new MoveCommand(registerState));
        this.registerCommandExecutor(new ResetCommand(registerState));
        this.registerCommandExecutor(new SetCommand(registerState));
    }

    private void registerCommandExecutor(CommandExecutor commandExecutor){
        this.commandExecutorMap.put(commandExecutor.getCommand(), commandExecutor);
    }

    public CommandExecutor getCommandExecutor(String input){
        Command command;
        try{
            command = Command.valueOf(input);
        }catch(Exception e){
            throw new CommandNotFoundException("Invalid command string");
        }
        if(this.commandExecutorMap.containsKey(command)){
            return commandExecutorMap.get(command);
        }
        throw new CommandNotFoundException("Command not found for input: " + command);
    }
}
