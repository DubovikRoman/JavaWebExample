package com.dubovik.library.model.entity;

import com.dubovik.library.model.utilities.IdGeneration;

import java.util.Arrays;

public class CustomBook extends Entity{
    private int id;
    private String title;
    private int year;
    private String[] authors;
    private String publishing_house;

    public CustomBook(int in_id, String in_title, int in_year, String[] in_authors, String in_publishing_house) {
        this.title = in_title;
        this.year = in_year;
        this.authors = in_authors;
        this.publishing_house = in_publishing_house;
        id = in_id;
    }

    public CustomBook( String in_title, int in_year, String[] in_authors, String in_publishing_house) {
        this.title = in_title;
        this.year = in_year;
        this.authors = in_authors;
        this.publishing_house = in_publishing_house;
        IdGeneration generation = new IdGeneration();
        id = generation.generateId();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String[] getAuthors() {
        return authors.clone();
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public String getPublishingHouse() {
        return publishing_house;
    }

    public void setPublishingHouse(String publishing_house) {
        this.publishing_house = publishing_house;
    }

    public int getId() {
        return id;
    }

    public boolean equals(CustomBook o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        return year == o.year &&
                title == o.title &&
                Arrays.equals(authors, o.authors) &&
                publishing_house == o.publishing_house;
    }

    public int hashCode() {
        int result = year * title.length();
        result = 31 * result + authors.length;
        return result;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder("CustomBook{");
        sb.append("title='").append(title).append('\'');
        sb.append(", year=").append(year);
        sb.append(", authors=").append(Arrays.toString(authors));
        sb.append(", publishing_house='").append(publishing_house).append('\'');
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
