package org.opcode.service.command.impl;

import org.opcode.constants.Command;
import org.opcode.repository.IRegisterState;
import org.opcode.service.command.CommandExecutor;

import java.util.List;

public class ResetCommand extends CommandExecutor  {

    private final IRegisterState registerState;

    public ResetCommand(IRegisterState registerState) {
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
