package org.opcode.service.command.impl;

import org.opcode.constants.Command;
import org.opcode.model.Register;
import org.opcode.model.RegisterState;
import org.opcode.service.command.CommandExecutor;
import org.opcode.utils.InputUtils;

import java.util.List;

public class ResetCommand extends CommandExecutor  {

    private final RegisterState registerState;

    public ResetCommand(RegisterState registerState) {
        this.registerState = registerState;
    }

    @Override
    public void execute(List<String> args) {
        registerState.reset();
    }

    @Override
    public Command getCommand(){
        return Command.RST;
    }

}
