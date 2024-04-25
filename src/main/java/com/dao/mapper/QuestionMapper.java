package com.dao.mapper;

import com.dao.pojo.Course;
import com.dao.pojo.Questions;
import com.mybatis.anation.*;

import java.util.List;


public interface QuestionMapper {
    @Insert("insert into questions(chapter_id,question_text,correct_answer)values(#{chapter_id},#{question_text},#{correct_answer})")
    void Insert(@Param("chapter_id") Integer chapter_id, @Param("question_text") String question_text, @Param("correct_answer") String correct_answer);

    @Select("select *from questions where course_id=#{course_id}")
    List<Questions> selectByChapterId(@Param("course_id") Integer course_id);


    @Update("UPDATE questions SET question_text = #{question_text}, correct_answer = #{correct_answer}WHERE id = #{id}")
    void UpdateInfo(@Param("question_text") String question_text, @Param("correct_answer") String correct_answer, @Param("id") Integer id);

    @Delete("DELETE FROM questions WHERE id = #{id}")
    void deleteById(@Param("id") Integer id);
}
