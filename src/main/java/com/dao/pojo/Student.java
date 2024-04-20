package com.dao.pojo;

public class Student {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Integer student_id;
    private Integer grande;
    private String introduction;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", student_id=" + student_id +
                ", grande=" + grande +
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

    public Integer getGrande() {
        return grande;
    }

    public void setGrande(Integer grande) {
        this.grande = grande;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Student(Integer id, String username, String password, String name, Integer student_id, Integer grande, String introduction) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.student_id = student_id;
        this.grande = grande;
        this.introduction = introduction;
    }

    public Student() {
    }
}
