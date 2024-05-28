package org.opcode.constants;

public enum Command {
    SET("SET",3),
    ADR("ADR",3),
    ADD("ADD",3),
    MOVE("MOV",3),
    INR("INR",2),
    DCR("DCR",2),
    RST("RST",1);

    private String commandString;
    private int expectedArguments;

    Command(String commandString, int expectedArguments){
        this.commandString = commandString;
        this.expectedArguments = expectedArguments;
    }

    public String getCommandString(){
        return this.commandString;
    }

    public int getExceptedArgumentsCount(){
        return this.expectedArguments;
    }
}
