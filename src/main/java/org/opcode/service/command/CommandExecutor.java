package org.opcode.service.command;

import org.opcode.constants.Command;
import org.opcode.utils.InputUtils;

import java.util.List;

public abstract class CommandExecutor {

    public final void executeCommands(List<String> args){
        // preProcess();
        InputUtils.validateArguments(args, getCommand().getExceptedArgumentsCount());
        execute(args);
    }

    protected abstract Command getCommand();
    protected abstract void execute(List<String> args);
    // abstract preProcess();
}
