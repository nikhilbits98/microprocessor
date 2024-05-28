package org.opcode.service.command.impl;

import org.opcode.constants.Command;
import org.opcode.model.Register;
import org.opcode.model.RegisterState;
import org.opcode.service.command.CommandExecutor;
import org.opcode.utils.InputUtils;

import java.util.List;

public class AddConstantCommand extends CommandExecutor {

    private final RegisterState registerState;

    public AddConstantCommand(RegisterState registerState) {
        this.registerState = registerState;
    }

    @Override
    public void execute(List<String> args) {
        Character registerName = InputUtils.validateAndGetRegisterName(args.get(1));
        int constantValue = InputUtils.validateAndParseInteger(args.get(2));
        Register register = registerState.getRegister(registerName);
        int newValue = register.getValue() + constantValue;
        register.setValue(newValue);
    }

    @Override
    public Command getCommand(){
        return Command.ADD;
    }
}
