package com.hostalmanagement.Web.Application.repository;

import com.hostalmanagement.Web.Application.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public interface NoticeRepo extends JpaRepository<Notice, Integer> {

    @Query(value ="SELECT * FROM viewNoticeFromView",nativeQuery = true)
    List<Notice>viewNoticeFromView();

    @Procedure(procedureName = "save_notice")
    void saveNoticeProcedure(
            @Param("p_id") Integer id,
            @Param("p_title") String title,
            @Param("p_content") String content,
            @Param("p_publish_date") Date publishDate,
            @Param("p_publish_time") Time publishTime);
}
