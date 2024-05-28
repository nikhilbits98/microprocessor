package org.opcode.service.command.impl;

import org.opcode.model.Register;
import org.opcode.model.RegisterState;
import org.opcode.service.command.CommandExecutor;
import org.opcode.utils.InputUtils;

import java.util.List;

public class ResetCommand implements CommandExecutor {

    private final RegisterState registerState;

    public ResetCommand(RegisterState registerState) {
        this.registerState = registerState;
    }

    @Override
    public void execute(List<String> args) {
        registerState.reset();
    }

    @Override
    public void validateArguments(List<String> args){
        InputUtils.validateArguments(args,1);
    }

}
