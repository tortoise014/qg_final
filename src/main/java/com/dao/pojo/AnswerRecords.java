package com.dao.pojo;

import java.util.Objects;

public class AnswerRecords {
    private Integer id;
    private Integer student_id;
    private Integer course_id;
    private String student_answer;
    private String score;
    private String time;
    private Integer question_id;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerRecords that = (AnswerRecords) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(student_id, that.student_id) &&
                Objects.equals(question_id, that.question_id) &&
                Objects.equals(course_id, that.course_id) &&
                Objects.equals(student_answer, that.student_answer) &&
                Objects.equals(score, that.score) &&
                Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, student_id, question_id, course_id, student_answer, score, time);
    }

    @Override
    public String toString() {
        return "AnswerRecords{" +
                "id=" + id +
                ", student_id=" + student_id +
                ", question_id=" + question_id +
                ", course_id=" + course_id +
                ", student_answer='" + student_answer + '\'' +
                ", score='" + score + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public AnswerRecords(Integer id, Integer student_id, Integer question_id, Integer course_id, String student_answer, String score, String time) {
        this.id = id;
        this.student_id = student_id;
        this.question_id = question_id;
        this.course_id = course_id;
        this.student_answer = student_answer;
        this.score = score;
        this.time = time;
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

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    }

    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    public String getStudent_answer() {
        return student_answer;
    }

    public void setStudent_answer(String student_answer) {
        this.student_answer = student_answer;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public AnswerRecords() {
    }
}
