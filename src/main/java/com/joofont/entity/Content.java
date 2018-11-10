package com.joofont.entity;

public class Content {

    private Integer id;

    private String contentName;

    private String content;

    private String createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", contentName='" + contentName + '\'' +
                ", content='" + content + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}