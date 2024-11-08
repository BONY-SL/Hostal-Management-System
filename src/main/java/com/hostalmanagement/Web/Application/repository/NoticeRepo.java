package com.hostalmanagement.Web.Application.repository;

import com.hostalmanagement.Web.Application.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface NoticeRepo extends JpaRepository<Notice, Integer> {

    @Query(value ="SELECT * FROM viewNoticeFromView",nativeQuery = true)
    List<Notice>viewNoticeFromView();
}
