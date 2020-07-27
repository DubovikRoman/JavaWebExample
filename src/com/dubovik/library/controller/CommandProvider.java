package com.dubovik.library.controller;

import com.dubovik.library.controller.impl.WrongCommand;

public class CommandProvider {

    public ActionCommand getCommand(String type_of_command){
        ActionCommand command = new WrongCommand();
        if(type_of_command != null){
            try {
                command = CommandType.valueOf(type_of_command.toUpperCase()).getCommand();
            }catch (IllegalArgumentException e){
                command = new WrongCommand();
            }
        }
        return command;
    }
}
