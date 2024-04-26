package com.dao.pojo;

import java.util.Objects;

public class StudentProgress {
    private Integer id;
    private Integer student_id;
    private Integer course_id;
    private Integer progress;
    private Integer grade;
    private Integer attendance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentProgress that = (StudentProgress) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(student_id, that.student_id) &&
                Objects.equals(course_id, that.course_id) &&
                Objects.equals(progress, that.progress) &&
                Objects.equals(grade, that.grade) &&
                Objects.equals(attendance, that.attendance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, student_id, course_id, progress, grade, attendance);
    }

    @Override
    public String toString() {
        return "StudentProgress{" +
                "id=" + id +
                ", student_id=" + student_id +
                ", course_id=" + course_id +
                ", progress=" + progress +
                ", grade=" + grade +
                ", attendance=" + attendance +
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

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getAttendance() {
        return attendance;
    }

    public void setAttendance(Integer attendance) {
        this.attendance = attendance;
    }

    public StudentProgress(Integer id, Integer student_id, Integer course_id, Integer progress, Integer grade, Integer attendance) {
        this.id = id;
        this.student_id = student_id;
        this.course_id = course_id;
        this.progress = progress;
        this.grade = grade;
        this.attendance = attendance;
    }

    public StudentProgress() {
    }
}
