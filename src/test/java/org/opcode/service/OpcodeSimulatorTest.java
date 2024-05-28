package org.opcode.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.opcode.model.Register;
import org.opcode.repository.IRegisterState;
import org.opcode.repository.impl.InMemoryRegisterState;
import org.opcode.service.impl.OpcodeSimulatorImpl;

@TestInstance (TestInstance.Lifecycle.PER_CLASS)
class OpcodeSimulatorTest {
    private OpcodeSimulator simulator;
    private IRegisterState registerState;

    @BeforeEach
    void setup() {
        List<Register> allRegisters = new ArrayList<>();
        allRegisters.add(new Register('A'));
        allRegisters.add(new Register('B'));
        allRegisters.add(new Register('C'));
        allRegisters.add(new Register('D'));
        this.registerState = new InMemoryRegisterState(allRegisters);
        this.simulator = new OpcodeSimulatorImpl(this.registerState);
    }

    @Test
    void testSetInstructions() {
        List<String> instructions = new ArrayList<>();
        instructions.add("RST");
        instructions.add("SET A 1");
        instructions.add("SET B -2");
        instructions.add("SET C 3");
        instructions.add("SET D 4");
        simulator.execute(instructions);
        assertEquals(1, this.registerState.getRegister('A').getValue());
        assertEquals(-2, this.registerState.getRegister('B').getValue());
        assertEquals(3, this.registerState.getRegister('C').getValue());
        assertEquals(4, this.registerState.getRegister('D').getValue());
    }

    @Test
    void testAddValueInstructions() {
        //      testing for subtraction
        List<String> instructions = new ArrayList<>();
        instructions.add("RST");
        instructions.add("SET A 11");
        instructions.add("ADD A -12");
        simulator.execute(instructions);
        assertEquals(-1, this.registerState.getRegister('A').getValue());
    }

    @Test
    void testAddRegisterInstructions() {
        List<String> instructions = new ArrayList<>();
        instructions.add("RST");
        instructions.add("SET C 5");
        instructions.add("SET D 2");
        instructions.add("ADR C D");
        simulator.execute(instructions);
        assertEquals(7, this.registerState.getRegister('C').getValue());
    }

    @Test
    void testMoveRegisterInstructions() {
        List<String> instructions = new ArrayList<>();
        instructions.add("RST");
        instructions.add("SET A 5");
        instructions.add("SET B 2");
        instructions.add("SET D 12");
        instructions.add("MOV B A");
        instructions.add("MOV D B");
        simulator.execute(instructions);
        assertEquals(5, this.registerState.getRegister('B').getValue());
        assertEquals(5, this.registerState.getRegister('D').getValue());
    }

    @Test
    void testIncrementDecrementRegisterInstructions() {
        List<String> instructions = new ArrayList<>();
        instructions.add("RST");
        instructions.add("SET A 5");
        instructions.add("SET B 2");
        instructions.add("INR A");
        instructions.add("DCR B");
        simulator.execute(instructions);
        assertEquals(6, this.registerState.getRegister('A').getValue());
        assertEquals(1, this.registerState.getRegister('B').getValue());
    }

    @Test
    void testResetRegisterInstructions() {
        List<String> instructions = new ArrayList<>();
        instructions.add("RST");
        instructions.add("SET A 5");
        instructions.add("SET B 2");
        instructions.add("SET C 3");
        instructions.add("SET D 4");
        instructions.add("RST");
        simulator.execute(instructions);
        assertEquals(0, this.registerState.getRegister('A').getValue());
        assertEquals(0, this.registerState.getRegister('B').getValue());
        assertEquals(0, this.registerState.getRegister('C').getValue());
        assertEquals(0, this.registerState.getRegister('D').getValue());
    }

    @Test
    void testMultipleInstructionsWithNOOP() {
        List<String> instructions = new ArrayList<>();
        instructions.add("RST");
        instructions.add("SET A 10");
        instructions.add("SET B 14");
        instructions.add("ADD B 12");
        instructions.add("INR A");
        instructions.add("DCR B");
        simulator.execute(instructions);
        assertEquals(11, this.registerState.getRegister('A').getValue());
        assertEquals(25, this.registerState.getRegister('B').getValue());
    }
}
