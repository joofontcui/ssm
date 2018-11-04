package com.joofont.entity;

/**
 * @author cui jun on 2018/10/28.
 * @version 1.0
 */
public class Book {

    private Integer id;

    private String name;

    private Double price;

    private Integer count;

    private String author;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", author='" + author + '\'' +
                '}';
    }

    public static int compareById(Book b1, Book b2) {
        if(b1.getId().equals(b2.getId())) {
            return b1.getCount().compareTo(b2.getCount());
        }
        return b1.getId().compareTo(b2.getId());
    }
}
