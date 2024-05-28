package org.opcode.service.command.impl;

import org.opcode.model.Register;
import org.opcode.model.RegisterState;
import org.opcode.service.command.CommandExecutor;
import org.opcode.utils.InputUtils;

import java.util.List;

// TODO: Get rid of this.
public class DecrementCommand implements CommandExecutor {

    private final RegisterState registerState;

    public DecrementCommand(RegisterState registerState) {
        this.registerState = registerState;
    }

    @Override
    public void execute(List<String> args) {
        Character registerName = InputUtils.validateAndGetRegisterName(args.get(1));
        int constantValue = -1;
        Register register = registerState.getRegister(registerName);
        int newValue = register.getValue() + constantValue;
        register.setValue(newValue);
    }

    @Override
    public void validateArguments(List<String> args){
        // TODO: Remove hardcoding.
        InputUtils.validateArguments(args, 2);
    }
}
