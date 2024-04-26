package com.dao.pojo;

import java.util.Objects;

public class StudentCourses {
    private Integer id;
    private Integer student_id;
    private Integer course_id;

    @Override
    public String toString() {
        return "StudentCourses{" +
                "id=" + id +
                ", student_id=" + student_id +
                ", course_id=" + course_id +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    public StudentCourses(Integer id, Integer student_id, Integer course_id) {
        this.id = id;
        this.student_id = student_id;
        this.course_id = course_id;
    }

    public StudentCourses() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentCourses that = (StudentCourses) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(student_id, that.student_id) &&
                Objects.equals(course_id, that.course_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, student_id, course_id);
    }
}
