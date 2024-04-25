package com.dao.mapper;

import com.dao.pojo.Course;
import com.mybatis.anation.*;

import java.util.List;

public interface CoursesMapper {
    @Select("select *from courses where teacher_id=#{teacher_id}")
    List<Course> selectByTeacherId(@Param("teacher_id") Integer teacher_id);
    @Select("select *from courses where name=#{name}")
    Course selectByCourseName(@Param("name") String name);

    @Insert("insert into courses(teacher_id,name,description,start_date,end_date,capacity)values(#{teacher_id},#{name},#{description},#{start_date},#{end_date},#{capacity})")
    void Insert(@Param("teacher_id") Integer teacher_id,@Param("name") String name,@Param("description") String description,@Param("start_date") String start_date,
                @Param("end_date")String end_date,@Param("capacity") Integer capacity);



    @Delete("DELETE FROM courses WHERE id = #{id}")
    void deleteCourseById(@Param("id") Integer id);

    @Update("UPDATE courses SET name=#{name}, description=#{description}, start_date=#{start_date}, end_date=#{end_date}, capacity=#{capacity} WHERE id=#{id}")
    void updateCourse(@Param("name") String name, @Param("description") String description, @Param("start_date") String start_date,
                      @Param("end_date") String end_date, @Param("capacity") Integer capacity,@Param("id") Integer id);
}
