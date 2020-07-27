package com.dubovik.library.controller.validator;

import com.dubovik.library.controller.CommandRequestParameters;

import java.util.Map;

public class CommandParametersValidator {

    public boolean validateParameters(Map<String, String> parameters){
        boolean is_correct = false;
        if(parameters.get(CommandRequestParameters.TITLE) != null
                && parameters.get(CommandRequestParameters.PUBLISHING_HOUSE) != null
                && parameters.get(CommandRequestParameters.AUTHORS) != null &&
                parameters.get(CommandRequestParameters.YEAR) != null){
            is_correct = true;
        }
        return is_correct;
    }
}
