package org.opcode.service.command.impl;

import org.opcode.constants.Command;
import org.opcode.model.Register;
import org.opcode.repository.IRegisterState;
import org.opcode.repository.impl.InMemoryRegisterState;
import org.opcode.service.command.CommandExecutor;
import org.opcode.utils.InputUtils;

import java.util.List;

public class SetCommand extends CommandExecutor  {

    private final IRegisterState registerState;

    public SetCommand(IRegisterState registerState) {
        this.registerState = registerState;
    }

    @Override
    public void execute(List<String> args) {
        Character registerName = InputUtils.validateAndGetRegisterName(args.get(1));
        int constantValue = InputUtils.validateAndParseInteger(args.get(2));
        Register register = registerState.getRegister(registerName);
        register.setValue(constantValue);
    }

    @Override
    public Command getCommand(){
        return Command.SET;
    }
}
