package com.dubovik.library.controller.impl;

import com.dubovik.library.controller.ActionCommand;
import com.dubovik.library.controller.CommandRequestParameters;
import com.dubovik.library.model.entity.CustomBook;
import com.dubovik.library.model.exception.ServiceException;
import com.dubovik.library.service.BookService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindByYearCommand implements ActionCommand {
    public Map<String, String> execute(Map<String, String> command_parameters) {
        Map<String, String> result = new HashMap<>();
        String find_key = command_parameters.get(CommandRequestParameters.KEY);
        try {
            List<CustomBook> find_results = BookService.getInstance().findByYear(Integer.valueOf(find_key));
            int index_of_book = 1;
            for(CustomBook book : find_results){
                result.put(String.valueOf(index_of_book), book.toString());
                index_of_book++;
            }
        } catch (ServiceException | NumberFormatException e) {
            result.put("status", e.toString());
        }
        return result;
    }
}
