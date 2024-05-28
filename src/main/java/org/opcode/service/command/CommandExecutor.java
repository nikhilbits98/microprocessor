package org.opcode.service.command;

import org.opcode.constants.Command;

import java.util.List;

public interface CommandExecutor {

    Command getCommand();
    void validateArguments(List<String> args);
    void execute(List<String> args);
}
