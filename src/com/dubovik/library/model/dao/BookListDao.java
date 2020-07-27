package com.dubovik.library.model.dao;

import com.dubovik.library.model.entity.CustomBook;
import com.dubovik.library.model.exception.DaoException;
import com.dubovik.library.model.exception.ServiceException;

import java.util.List;

public interface BookListDao extends BaseDao <CustomBook> {
    boolean add (CustomBook add_book) throws DaoException;
    boolean remove(CustomBook book) throws DaoException;
    List<CustomBook> findByTitle(String title) throws DaoException;
    List<CustomBook> findByYear(int year) throws DaoException;
}

