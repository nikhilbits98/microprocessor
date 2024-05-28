package org.opcode.service.command.impl;

import org.opcode.exceptions.ArgumentValidationException;
import org.opcode.model.Register;
import org.opcode.model.RegisterState;
import org.opcode.service.command.CommandExecutor;
import org.opcode.utils.InputUtils;

import java.util.List;

public class AddRegisterCommand implements CommandExecutor {

    private final RegisterState registerState;

    public AddRegisterCommand(RegisterState registerState) {
        this.registerState = registerState;
    }

    @Override
    public void execute(List<String> args) {
        Character destinationRegisterName = InputUtils.validateAndGetRegisterName(args.get(1));
        Character sourceRegisterName = InputUtils.validateAndGetRegisterName(args.get(2));
        Register destinationRegister = registerState.getRegister(destinationRegisterName);
        Register sourceRegister = registerState.getRegister(sourceRegisterName);
        int newValue = sourceRegister.getValue() + destinationRegister.getValue();
        destinationRegister.setValue(newValue);
    }

    @Override
    public void validateArguments(List<String> args){
        InputUtils.validateArguments(args,3);
    }
}
