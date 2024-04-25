package com.dao.pojo;

public class Chapter {
    private Integer id;
    private Integer course_id;
    private String title;
    private String content;

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", course_id=" + course_id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Chapter(Integer id, Integer course_id, String title, String content) {
        this.id = id;
        this.course_id = course_id;
        this.title = title;
        this.content = content;
    }

    public Chapter() {
    }
}
