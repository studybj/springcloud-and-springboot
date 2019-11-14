package com.bj.study.springboot.entity;

import org.springframework.stereotype.Component;

@Component
public class Book {
    private String bookName;
    private String author;
    private String bookurl;
    public Book(){}
    public Book(String bookName, String author, String bookurl) {
        this.bookName = bookName;
        this.author = author;
        this.bookurl = bookurl;
    }
    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getBookurl() {
        return bookurl;
    }
    public void setBookurl(String bookurl) {
        this.bookurl = bookurl;
    }
}
