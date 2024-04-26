package com.dao.pojo;

import java.util.Objects;

public class Questions {
    private Integer id;
    private Integer course_id;
    private String question_text;
    private String correct_answer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Questions questions = (Questions) o;
        return Objects.equals(id, questions.id) &&
                Objects.equals(course_id, questions.course_id) &&
                Objects.equals(question_text, questions.question_text) &&
                Objects.equals(correct_answer, questions.correct_answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, course_id, question_text, correct_answer);
    }

    @Override
    public String toString() {
        return "Questions{" +
                "id=" + id +
                ", course_id=" + course_id +
                ", question_text='" + question_text + '\'' +
                ", correct_answer='" + correct_answer + '\'' +
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

    public String getQuestion_text() {
        return question_text;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public Questions(Integer id, Integer course_id, String question_text, String correct_answer) {
        this.id = id;
        this.course_id = course_id;
        this.question_text = question_text;
        this.correct_answer = correct_answer;
    }

    public Questions() {
    }
}
