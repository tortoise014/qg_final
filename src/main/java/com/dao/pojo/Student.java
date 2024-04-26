package com.dao.pojo;

import java.util.Objects;

public class Student {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Integer student_id;
    private Integer grade;
    private String introduction;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) &&
                Objects.equals(username, student.username) &&
                Objects.equals(password, student.password) &&
                Objects.equals(name, student.name) &&
                Objects.equals(student_id, student.student_id) &&
                Objects.equals(grade, student.grade) &&
                Objects.equals(introduction, student.introduction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, name, student_id, grade, introduction);
    }

    public Student(Integer id, String username, String password, String name, Integer student_id, Integer grade, String introduction) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.student_id = student_id;
        this.grade = grade;
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", student_id=" + student_id +
                ", grade=" + grade +
                ", introduction='" + introduction + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Student() {
    }
}
