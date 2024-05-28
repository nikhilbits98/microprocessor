package org.opcode.utils;

import org.opcode.exceptions.ArgumentValidationException;

import java.util.List;

public class InputUtils {

    public static Character validateAndGetRegisterName(String arg){
        if(arg == null || arg.length() > 1){
            throw new ArgumentValidationException("Invalid input for register name.");
        }
        return arg.charAt(0);
    }

    public static void validateArguments(List<String> args, int expectedArgCount){
        if(args == null || args.size() != expectedArgCount){
            throw new ArgumentValidationException("Bad input command: Number of expected arguments do not match");
        }
    }

    public static int validateAndParseInteger(String integerString){
        try{
            return Integer.parseInt(integerString);
        }catch(NumberFormatException exception){
            throw new ArgumentValidationException("Bad Input command: Input value was not integer.");
        }
    }
}
