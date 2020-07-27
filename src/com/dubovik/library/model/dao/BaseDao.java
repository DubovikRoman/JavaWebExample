package com.dubovik.library.model.dao;

import com.dubovik.library.model.entity.Entity;
import com.dubovik.library.model.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface BaseDao<T extends Entity> {
    boolean add(T t) throws DaoException;
    boolean remove(T t) throws DaoException;
    List<T> findAll(String key) throws DaoException;
    T findById(long id) throws DaoException;

    default void close(Statement statement) throws DaoException {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DaoException("Error while close statement", e);
            }
        }
    }

    default void close(Connection connection) throws DaoException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException("Error while close connection", e);
            }
        }
    }
}
