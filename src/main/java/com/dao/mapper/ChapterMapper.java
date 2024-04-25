package com.dao.mapper;

import com.dao.pojo.Chapter;
import com.mybatis.anation.*;

import java.util.List;

public interface ChapterMapper {
    @Select("select *from chapters where course_id=#{course_id}")
    List<Chapter> selectAllByCourseId(@Param("course_id") Integer course_id);
    @Update("UPDATE chapters SET title = #{title}, content = #{content} WHERE id = #{chapterId}")
    void update(@Param("title") String title,@Param("content") String content,@Param("chapterId") Integer chapterId);
    @Insert("insert INTO chapters (course_id, title,content) VALUES (#{course_id}, #{title},#{content})")
    void addChapter(@Param("course_id")Integer course_id,@Param("title") String title,@Param("content") String content);
    @Delete("delete from chapters where id=#{id}")
    void delete(@Param("id") Integer id);

}
