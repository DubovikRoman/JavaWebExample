package com.dubovik.library.service;

import com.dubovik.library.model.dao.impl.BookListDaoImpl;
import com.dubovik.library.model.entity.CustomBook;
import com.dubovik.library.model.exception.DaoException;
import com.dubovik.library.model.exception.ServiceException;

import java.util.List;

public class BookService {

    private static BookService instance = new BookService();

    public static BookService getInstance() {
        return instance;
    }

    private BookService(){
    }

    public void addBook(CustomBook add_book) throws ServiceException {
        if(add_book == null){
            throw new ServiceException("Add book is null");
        }
        try{
            BookListDaoImpl.getInstance().add(add_book);
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public void removeBook(CustomBook replace_book) throws ServiceException {
        if(replace_book == null){
            throw new ServiceException("Replace book is null");
        }
        try{
            BookListDaoImpl.getInstance().remove(replace_book);
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public CustomBook findById(long id) throws ServiceException {
        if(id < 0){
            throw new ServiceException("Id is incorrect");
        }
        CustomBook book = null;
        try {
            book = BookListDaoImpl.getInstance().findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return book;
    }

    public List<CustomBook> findByTitle(String title) throws ServiceException{
        if(title == null){
            throw new ServiceException("Find title string is null");
        }
        List<CustomBook> books_by_title = null;
        try {
            books_by_title = BookListDaoImpl.getInstance().findByTitle(title);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return books_by_title;
    }

    public List<CustomBook> findByYear(int year) throws ServiceException{
        List<CustomBook> books_by_title = null;
        try {
            books_by_title = BookListDaoImpl.getInstance().findByYear(year);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return books_by_title;
    }

    public List<CustomBook> sort(String key) throws ServiceException{
        if(key == null){
            throw new ServiceException("Sort key = null");
        }
        List<CustomBook> sorted_books;
        try {
            sorted_books = BookListDaoImpl.getInstance().findAll(key);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return sorted_books;
    }
}
