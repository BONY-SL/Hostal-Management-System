package com.hostalmanagement.Web.Application.service;

import com.hostalmanagement.Web.Application.dto.NoticeDto;
import com.hostalmanagement.Web.Application.model.Notice;
import com.hostalmanagement.Web.Application.repository.NoticeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepo noticeRepo;

    public void saveNotice(NoticeDto noticeDto) {
        // Get current date and time
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        noticeRepo.saveNoticeProcedure(
                noticeDto.getId(),
                noticeDto.getTitle(),
                noticeDto.getContent(),
                Date.valueOf(currentDate),
                Time.valueOf(currentTime));
    }

    public List<NoticeDto> viewNotices(){
        List<Notice> noticeList=noticeRepo.viewNoticeFromView();
        return noticeList.stream().map(this::viewNoticeConvertToDto).collect(Collectors.toList());
    }

    private NoticeDto viewNoticeConvertToDto(Notice notice) {
        return NoticeDto.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .publishDate(notice.getPublishDate())
                .publishTime(notice.getPublishTime())
                .build();
    }

    public void updateNotices(NoticeDto noticeDto) {
        Notice notice=noticeRepo.findById(noticeDto.getId()).orElseThrow();
        notice.setId(noticeDto.getId());
        notice.setTitle(noticeDto.getTitle());
        notice.setContent(noticeDto.getContent());
        notice.setPublishDate(noticeDto.getPublishDate());
        notice.setPublishTime(noticeDto.getPublishTime());

        noticeRepo.save(notice);
    }

}
