package com.dubovik.library.controller.impl;

import com.dubovik.library.controller.ActionCommand;
import com.dubovik.library.controller.CommandRequestParameters;
import com.dubovik.library.model.entity.CustomBook;
import com.dubovik.library.model.exception.ServiceException;
import com.dubovik.library.service.BookService;

import java.util.HashMap;
import java.util.Map;

public class FindByIdCommand implements ActionCommand {
    public Map<String, String> execute(Map<String, String> command_parameters) {
        Map<String, String> result = new HashMap<>();
        String find_key = command_parameters.get(CommandRequestParameters.KEY);
        try {
            CustomBook find_results = BookService.getInstance().findById(Long.valueOf(find_key));
            if(find_results != null){
                result.put("found book", find_results.toString());
            }
            else{
                result.put("status", "there is no book with id: " + find_key);
            }
        } catch (ServiceException | NumberFormatException e) {
            result.put("status", e.toString());
        }
        return result;
    }
}
