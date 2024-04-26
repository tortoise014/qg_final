package com.dao.pojo;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chapter chapter = (Chapter) o;
        return Objects.equals(id, chapter.id) &&
                Objects.equals(course_id, chapter.course_id) &&
                Objects.equals(title, chapter.title) &&
                Objects.equals(content, chapter.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, course_id, title, content);
    }
}
