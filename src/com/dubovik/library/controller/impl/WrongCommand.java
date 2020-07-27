package com.dubovik.library.controller.impl;

import com.dubovik.library.controller.ActionCommand;

import java.util.HashMap;
import java.util.Map;

public class WrongCommand implements ActionCommand {

    public Map<String, String> execute(Map<String, String> command_parameters) {
        Map<String, String> result = new HashMap<>();
        result.put("status", "There is no such command");
        return result;
    }
}
