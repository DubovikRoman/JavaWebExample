package test.dubovik.library.controller;

import com.dubovik.library.controller.Controller;
import com.dubovik.library.model.dao.BookListDao;
import com.dubovik.library.model.dao.impl.BookListDaoImpl;
import com.dubovik.library.model.entity.BookList;
import com.dubovik.library.model.entity.CustomBook;
import com.dubovik.library.model.exception.DaoException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class ControllerTest {

    @Test
    public void testDoBookCommandAdd() throws DaoException {
        BookList.getInstance().clear();
        String type_command = "ADD_BOOK";
        Map<String, String> command_parameters = new HashMap<>();
        command_parameters.put("title", "Дифференциальные уравнения");
        command_parameters.put("authors", "Альсевич Мазаник");
        command_parameters.put("publishing house", "БГУ");
        command_parameters.put("year", "2012");
        command_parameters = Controller.getInstance().doBookCommand(type_command, command_parameters);
    }

    @Test
    public void testDoBookCommandAddFail() throws DaoException {
        BookList.getInstance().clear();
        String type_command = "ADD_BOOK";
        Map<String, String> command_parameters = new HashMap<>();
        command_parameters.put("title", "Дифференциальные уравнения");
        command_parameters.put("authors", "Альсевич Мазаник");
        command_parameters.put("publishing house", "БГУ");
        command_parameters.put("year", null);
        command_parameters = Controller.getInstance().doBookCommand(type_command, command_parameters);
        Map<String, String> expected = new HashMap<>();
        expected.put("status", "com.dubovik.library.model.exception.ServiceException: Add book is null");
        Assert.assertEquals(command_parameters, expected);
    }

    @Test
    public void testDoBookRemoveCommand() throws DaoException {
        BookList.getInstance().clear();
        String type_command = "REMOVE_BOOK";
        Map<String, String> command_parameters = new HashMap<>();
        command_parameters.put("title", "Дифференциальные уравнения");
        command_parameters.put("authors", "Альсевич Мазаник");
        command_parameters.put("publishing house", "БГУ");
        command_parameters.put("year", "2012");
        command_parameters = Controller.getInstance().doBookCommand(type_command, command_parameters);
        Map<String, String> expected = new HashMap<>();
        expected.put("status", "Book is removed successfully");
        Assert.assertEquals(command_parameters, expected);
    }

    @Test
    public void testDoBookRemoveCommandFail() throws DaoException {
        BookList.getInstance().clear();
        String type_command = "REMOVE_BOOK";
        Map<String, String> command_parameters = new HashMap<>();
        command_parameters.put("title", "Дифференциальные уравнения");
        command_parameters.put("authors", "Альсевич Мазаник");
        command_parameters.put("publishing house", "БГУ");
        command_parameters.put("year", "2012");
        command_parameters = Controller.getInstance().doBookCommand(type_command, command_parameters);
        Map<String, String> expected = new HashMap<>();
        expected.put("status", "There is no such book to delete");
        Assert.assertEquals(command_parameters, expected);
    }

    @Test
    public void testDoBookFindCommand() throws DaoException {
        String type_command = "FIND_BY_ID";
        Map<String, String> command_parameters = new HashMap<>();
        command_parameters.put("key", "11");
        Map<String, String> found = Controller.getInstance().doBookCommand(type_command, command_parameters);
        Map<String, String> expected = new HashMap<>();
        expected.put("status", "there is no book with id: 11");
        Assert.assertEquals(found, expected);
    }

    @Test
    public void testDoBookFindCommandTitle() throws DaoException {
        String type_command = "FIND_BY_TITLE";
        Map<String, String> command_parameters = new HashMap<>();
        command_parameters.put("key", "Геометрические уравнения");
        Map<String, String> found = Controller.getInstance().doBookCommand(type_command, command_parameters);
        int expected_count_of_found = 2;
        Assert.assertEquals(expected_count_of_found, found.size());
    }

    @Test
    public void testDoBookSortCommand() throws DaoException {
        String type_command = "SORT";
        Map<String, String> command_parameters = new HashMap<>();
        command_parameters.put("key", "title");
        Map<String, String> found = Controller.getInstance().doBookCommand(type_command, command_parameters);
    }
}