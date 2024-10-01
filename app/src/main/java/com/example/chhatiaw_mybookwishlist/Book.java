package com.example.chhatiaw_mybookwishlist;

import java.io.Serializable;

public class Book implements Serializable {

    private String bookName;
    private String authorName;

    private String genre;

    private Integer year;

    private Boolean readingStatus; //READ OR UNREAD

    public Book(String book_name, String author_name, String genre, Integer year, Boolean reading_status)
    {
        this.bookName = book_name;
        this.authorName = author_name;
        this.genre = genre;
        this.year = year;
        this.readingStatus = reading_status;
    }

    public void setBookName(String bookName){this.bookName=bookName;}
    public void setAuthorName(String authorName){this.authorName=authorName;}
    public void setGenre(String genre){this.genre=genre;}

    public void setYear(Integer year){this.year=year;}

    public void setReadingStatus(Boolean readingStatus){this.readingStatus=readingStatus;}

    public String getBookName(){return bookName;}
    public String getAuthorName(){return authorName;}
    public String getGenre(){return genre;}

    public Integer getYear(){return year;}

    public Boolean getReadingStatus(){return readingStatus;}
}
