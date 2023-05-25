package com.example.springapi.api.model;

public class Book {

    private int bookId;
    private String Title;
    private String authorName;
    private double price;
    private Category category;

    public Book() {
    }

    public Book(int bookId, String Title, String authorName, double price, Category category) {
        this.bookId = bookId;
        this.Title = Title;
        this.authorName = authorName;
        this.price = price;
        this.category = category;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
