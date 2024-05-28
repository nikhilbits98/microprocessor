package org.opcode.service.command.impl;

import org.opcode.constants.Command;
import org.opcode.model.Register;
import org.opcode.repository.IRegisterState;
import org.opcode.repository.impl.InMemoryRegisterState;
import org.opcode.service.command.CommandExecutor;
import org.opcode.utils.InputUtils;

import java.util.List;

// TODO: Get rid of this.
public class IncrementCommand extends CommandExecutor {

    private final IRegisterState registerState;

    public IncrementCommand(IRegisterState registerState) {
        this.registerState = registerState;
    }

    @Override
    public void execute(List<String> args) {
        Character registerName = InputUtils.validateAndGetRegisterName(args.get(1));
        int constantValue = 1;
        Register register = registerState.getRegister(registerName);
        int newValue = register.getValue() + constantValue;
        register.setValue(newValue);
    }

    @Override
    public Command getCommand(){
        return Command.INR;
    }
}
