package com.dubovik.library.model.dao.impl;

import com.dubovik.library.controller.CommandRequestParameters;
import com.dubovik.library.model.connection.SqlConnection;
import com.dubovik.library.model.dao.BookListDao;
import com.dubovik.library.model.entity.CustomBook;
import com.dubovik.library.model.exception.DaoException;
import com.dubovik.library.model.exception.ServiceException;
import com.dubovik.library.service.BookService;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookListDaoImpl implements BookListDao {
    private static BookListDaoImpl instance = new BookListDaoImpl();

    public static BookListDaoImpl getInstance() {
        return instance;
    }

    private final static String SQL_ADD_BOOK = "INSERT INTO books(book_id, title, year, authors, publishing_house)" +
            "VALUES(?, ?, ?, ?, ?)";
    private final static String SQL_REMOVE_BOOK = "DELETE FROM books WHERE book_id = ? AND title = ? AND year = ?" +
            " AND authors = ? AND publishing_house = ?";
    private static final String SQL_FIND_BOOKS_BY_ID = "SELECT book_id, title, year, authors, " +
            " publishing_house FROM books WHERE book_id = ? ORDER BY book_id";
    private static final String SQL_FIND_BOOKS_BY_TITLE = "SELECT book_id, title, year, authors, " +
            " publishing_house FROM books WHERE title = ? ORDER BY title";
    private static final String SQL_FIND_BOOKS_BY_YEAR = "SELECT book_id, title, year, authors, " +
            " publishing_house FROM books year = ? ORDER BY year";
    private static final String SQL_SORT_BOOKS_BY_KEY = "SELECT book_id, title, year, authors, " +
            " publishing_house FROM books ORDER BY ";

    public boolean add(CustomBook book) throws DaoException {
        SqlConnection sql_connection = new SqlConnection();
        Connection connection = null;
        PreparedStatement statement = null;
        int changed_count = 0;
        try{
            connection = sql_connection.connect();
            statement = connection.prepareStatement(SQL_ADD_BOOK);
            statement.setInt(1, book.getId());
            statement.setString(2, book.getTitle());
            statement.setInt(3, book.getYear());
            statement.setString(4, Arrays.toString(book.getAuthors()));
            statement.setString(5, book.getPublishingHouse());
            changed_count = statement.executeUpdate();
        } catch (SQLException e) {
           throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return changed_count > 0;
    }

    public boolean remove(CustomBook book) throws DaoException {
        SqlConnection sql_connection = new SqlConnection();
        Connection connection = null;
        PreparedStatement statement = null;
        int changed_count = 0;
        try{
            connection = sql_connection.connect();
            statement = connection.prepareStatement(SQL_REMOVE_BOOK);
            statement.setInt(1, book.getId());
            statement.setString(2, book.getTitle());
            statement.setInt(3, book.getYear());
            statement.setString(4, Arrays.toString(book.getAuthors()));
            statement.setString(5, book.getPublishingHouse());
            changed_count = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return changed_count > 0;
    }

    public List<CustomBook> findAll(String key) throws DaoException {
        SqlConnection sql_connection = new SqlConnection();
        Connection connection = null;
        PreparedStatement statement = null;
        CustomBook book = null;
        List<CustomBook> books = new ArrayList<>();
        String sort_by_key = SQL_SORT_BOOKS_BY_KEY + key;
        try{
            connection = sql_connection.connect();
            statement = connection.prepareStatement(sort_by_key);
            ResultSet result_set = statement.executeQuery();
            while(result_set.next()){
                books.add(book = createBook(result_set));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return books;
    }

    public CustomBook findById(long id) throws DaoException {
        SqlConnection sql_connection = new SqlConnection();
        Connection connection = null;
        PreparedStatement statement = null;
        CustomBook book = null;
        try{
            connection = sql_connection.connect();
            statement = connection.prepareStatement(SQL_FIND_BOOKS_BY_ID);
            statement.setLong(1, id);
            ResultSet result_set = statement.executeQuery();
            while(result_set.next()){
                book = createBook(result_set);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return book;
    }

    public List<CustomBook> findByTitle(String title) throws DaoException{
        SqlConnection sql_connection = new SqlConnection();
        Connection connection = null;
        PreparedStatement statement = null;
        CustomBook book = null;
        List<CustomBook> books = new ArrayList<>();
        try{
            connection = sql_connection.connect();
            statement = connection.prepareStatement(SQL_FIND_BOOKS_BY_TITLE);
            statement.setString(1, title);
            ResultSet result_set = statement.executeQuery();
            while(result_set.next()){
                books.add(book = createBook(result_set));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return books;
    }

    public List<CustomBook> findByYear(int year) throws DaoException {
        SqlConnection sql_connection = new SqlConnection();
        Connection connection = null;
        PreparedStatement statement = null;
        CustomBook book = null;
        List<CustomBook> books = new ArrayList<>();
        try{
            connection = sql_connection.connect();
            statement = connection.prepareStatement(SQL_FIND_BOOKS_BY_YEAR);
            statement.setInt(1, year);
            ResultSet result_set = statement.executeQuery();
            while(result_set.next()){
                books.add(book = createBook(result_set));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return books;
    }

    private CustomBook createBook(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        String title = resultSet.getString(2);
        int year = resultSet.getInt(3);
        String[] authors = resultSet.getString(4).split(" ");
        String publishing_house = resultSet.getString(5);
        CustomBook book = new CustomBook(id, title, year, authors, publishing_house);
        return book;
    }
}
