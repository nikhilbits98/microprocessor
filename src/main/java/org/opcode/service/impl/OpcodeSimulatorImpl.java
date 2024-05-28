package org.opcode.service.impl;

import org.opcode.exceptions.ArgumentValidationException;
import org.opcode.repository.IRegisterState;
import org.opcode.repository.impl.InMemoryRegisterState;
import org.opcode.service.OpcodeSimulator;
import org.opcode.service.command.CommandExecutor;
import org.opcode.service.command.CommandFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OpcodeSimulatorImpl implements OpcodeSimulator {

    private final CommandFactory commandFactory;

    public OpcodeSimulatorImpl(IRegisterState registerState) {
        this.commandFactory = new CommandFactory(registerState);
    }

    @Override
    public void execute(List<String> instructions) {
        for(String instruction : instructions){
            List<String> arguments = Arrays.stream(instruction.split(" ")).collect(Collectors.toList());
            if(arguments.isEmpty()){
                throw new ArgumentValidationException("Invalid command input.");
            }
            CommandExecutor commandExecutor = commandFactory.getCommandExecutor(arguments.get(0));
            commandExecutor.executeCommands(arguments);
        }
    }
}
