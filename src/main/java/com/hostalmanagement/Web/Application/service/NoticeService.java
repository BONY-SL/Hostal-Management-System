package com.hostalmanagement.Web.Application.service;

import com.hostalmanagement.Web.Application.dto.NoticeDto;
import com.hostalmanagement.Web.Application.model.Notice;
import com.hostalmanagement.Web.Application.repository.NoticeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepo noticeRepo;

    public Notice saveNotice(NoticeDto noticeDto) {
        Notice notice=Notice.builder()
                .id(noticeDto.getId())
                .title(noticeDto.getTitle())
                .content(noticeDto.getContent())
                .publishDate(noticeDto.getPublishDate())
                .publishTime(noticeDto.getPublishTime())
                .build();
        return noticeRepo.save(notice);

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
