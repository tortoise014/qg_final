package com.dao.mapper;

import com.dao.pojo.AnswerRecords;

import com.mybatis.anation.Insert;
import com.mybatis.anation.Param;
import com.mybatis.anation.Select;

import java.util.List;

public interface AnswerRecordMapper {
    @Select("select *from answer_records where student_id=#{student_id}")
    List<AnswerRecords> selectByStuId(@Param("student_id") Integer student_id);

    @Select("select *from answer_records where course_id=#{course_id}")
    List<AnswerRecords> selectByCourseId(@Param("course_id") Integer course_id);
    @Insert("insert INTO answer_records (student_id,course_id,student_answer,score,time,question_id) VALUES (#{student_id},#{course_id},#{student_answer}, #{score},#{time},#{question_id})")
    void addRecords(@Param("student_id")Integer student_id,@Param("course_id") Integer course_id,@Param("student_answer") String student_answer
            ,@Param("score") String score,@Param("time") String time,@Param("question_id") Integer question_id);

}
