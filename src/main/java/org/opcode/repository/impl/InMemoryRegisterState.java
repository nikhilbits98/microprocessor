package org.opcode.repository.impl;

import org.opcode.model.Register;
import org.opcode.repository.IRegisterState;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryRegisterState implements IRegisterState {
    private Map<Character, Register> registers;

    public InMemoryRegisterState(List<Register> registers) {
        this.registers = registers.stream().collect(Collectors.toMap(Register::getName, r -> r));
    }

    public void addRegister(Register register){
        if (!registers.containsKey(register.getName())) {
            throw new RuntimeException(String.format("The register %s is already present", register.getName()));
        }
        registers.put(register.getName(), register);
    }

    public void updateValue(Register register) {
        if (!registers.containsKey(register.getName())) {
            throw new RuntimeException(String.format("The register %s is not present", register.getName()));
        }
        registers.put(register.getName(), register);
    }

    public Register getRegister(Character registerChar) {
        if (!registers.containsKey(registerChar)) {
            throw new RuntimeException(String.format("The register %s is not present", registerChar));
        }
        return registers.get(registerChar);
    }

    public void reset() {
        registers.replaceAll((k, v) -> {
            v.setValue(0);
            return v;
        });
    }
}
