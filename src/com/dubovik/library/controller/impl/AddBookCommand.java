package com.dubovik.library.controller.impl;

import com.dubovik.library.controller.ActionCommand;
import com.dubovik.library.controller.CommandRequestParameters;
import com.dubovik.library.controller.validator.CommandParametersValidator;
import com.dubovik.library.model.entity.CustomBook;
import com.dubovik.library.model.exception.ServiceException;
import com.dubovik.library.model.utilities.IdGeneration;
import com.dubovik.library.service.BookService;

import java.util.HashMap;
import java.util.Map;

public class AddBookCommand implements ActionCommand {

    public Map<String, String> execute(Map<String, String> command_parameters) {
        Map<String, String> result = new HashMap<>();
        CommandParametersValidator validator = new CommandParametersValidator();
        CustomBook add_book = null;
        try{
            if(validator.validateParameters(command_parameters)){
                String title = command_parameters.get(CommandRequestParameters.TITLE);
                String publishing_house = command_parameters.get(CommandRequestParameters.PUBLISHING_HOUSE);
                String[] authors = command_parameters.get(CommandRequestParameters.AUTHORS).
                        split(CommandRequestParameters.DELIMITERS);
                int year = Integer.valueOf(command_parameters.get(CommandRequestParameters.YEAR));
                add_book = new CustomBook( title, year, authors, publishing_house);
            }
            BookService.getInstance().addBook(add_book);
            result.put("status", "Book is added successfully");
        } catch (ServiceException | NumberFormatException e) {
            result.put("status", e.toString());
        }
        return result;
    }
}
