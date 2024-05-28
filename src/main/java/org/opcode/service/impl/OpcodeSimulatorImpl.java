package org.opcode.service.impl;

import org.opcode.exceptions.ArgumentValidationException;
import org.opcode.model.RegisterState;
import org.opcode.service.OpcodeSimulator;
import org.opcode.service.command.CommandExecutor;
import org.opcode.service.command.CommandFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OpcodeSimulatorImpl implements OpcodeSimulator {

    private final CommandFactory commandFactory;
    private final RegisterState registerState;

    public OpcodeSimulatorImpl(CommandFactory commandFactory, RegisterState registerState) {
        this.commandFactory = commandFactory;
        this.registerState = registerState;
    }

    @Override
    public RegisterState execute(List<String> instructions) {
        for(String instruction : instructions){
            List<String> arguments = Arrays.stream(instruction.split(" ")).collect(Collectors.toList());
            if(arguments.isEmpty()){
                throw new ArgumentValidationException("Invalid command input.");
            }
            CommandExecutor commandExecutor = commandFactory.getCommandExecutor(arguments.get(0));
            commandExecutor.validateArguments(arguments);
            commandExecutor.execute(arguments);
        }
        return registerState;
    }
}
