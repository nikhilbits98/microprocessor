package org.opcode.repository;

import org.opcode.model.Register;

public interface IRegisterState {

    void addRegister(Register register);
    void updateValue(Register register);
    Register getRegister(Character registerChar);
    void reset();
}
