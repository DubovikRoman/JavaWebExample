package com.dubovik.library.controller;

import com.dubovik.library.model.dao.impl.BookListDaoImpl;

import java.util.Map;

public class Controller {

    private static Controller instance = new Controller();

    private Controller(){
    }

    public static Controller getInstance() {
        return instance;
    }

    public Map<String, String> doBookCommand(String type_of_command, Map<String, String> list_of_commands){
        CommandProvider provider = new CommandProvider();
        ActionCommand command = provider.getCommand(type_of_command);
        Map<String, String> result = command.execute(list_of_commands);
        return result;
    }
}
