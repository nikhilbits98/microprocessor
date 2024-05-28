package org.opcode.service.command.impl;

import org.opcode.constants.Command;
import org.opcode.model.Register;
import org.opcode.model.RegisterState;
import org.opcode.service.command.CommandExecutor;
import org.opcode.utils.InputUtils;

import java.util.List;

public class MoveCommand extends CommandExecutor  {

    private final RegisterState registerState;

    public MoveCommand(RegisterState registerState) {
        this.registerState = registerState;
    }

    @Override
    public void execute(List<String> args) {
        Character destinationRegisterName = InputUtils.validateAndGetRegisterName(args.get(1));
        Character sourceRegisterName = InputUtils.validateAndGetRegisterName(args.get(2));
        Register destinationRegister = registerState.getRegister(destinationRegisterName);
        Register sourceRegister = registerState.getRegister(sourceRegisterName);
        destinationRegister.setValue(sourceRegister.getValue());
    }

    @Override
    public Command getCommand(){
        return Command.MOV;
    }
}
