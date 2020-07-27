package com.dubovik.library.model.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookList {
    private static int SIZE = 250;
    private static BookList instance = new BookList(SIZE);
    private List<CustomBook> books;

    public static BookList getInstance() {
        return instance;
    }

    private BookList(int size){
        books = new ArrayList<>(size);
    }

    public List<CustomBook> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public boolean setBooks(List<CustomBook> books) {
        boolean is_correct = false;
        if(books.size() <= SIZE){
            is_correct = true;
            this.books = books;
        }
        return is_correct;
    }

    public int findCurrentCountBooks(){
        return books.size();
    }

    private boolean isIndexRight(int index){
        boolean is_right = false;
        if(index >= 0 && index <= SIZE){
            is_right = true;
        }
        return is_right;
    }

    public CustomBook getByIndex(int index){
        if(!isIndexRight(index)){
            throw new IndexOutOfBoundsException("Incorrect index");
        }
        return books.get(index);
    }

    public boolean setByIndex(int index, CustomBook replacement){
        boolean is_correct = false;
        if(isIndexRight(index)){
            books.set(index, replacement);
            is_correct = true;
        }
        return is_correct;
    }

    public boolean addBook(CustomBook new_book){
        boolean is_correct = false;
        if(findCurrentCountBooks() < SIZE){
            is_correct = true;
            books.add(new_book);
        }
        return is_correct;
    }

    public boolean deleteBook(int index){
        boolean is_correct = false;
        if(isIndexRight(index)){
            books.remove(index);
            is_correct = true;
        }
        return is_correct;
    }

    public void clear(){
        books.clear();
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder("BookList{");
        sb.append("books=").append(books);
        sb.append(books.toString());
        sb.append('}');
        return sb.toString();
    }
}
