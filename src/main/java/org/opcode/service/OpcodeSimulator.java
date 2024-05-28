package org.opcode.service;

import java.util.List;
import org.opcode.repository.impl.InMemoryRegisterState;

public interface OpcodeSimulator {
    void execute(List<String> instructions);
}
