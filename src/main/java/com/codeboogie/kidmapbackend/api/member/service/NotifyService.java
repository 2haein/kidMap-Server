package com.codeboogie.kidmapbackend.api.member.service;

import com.codeboogie.kidmapbackend.common.member.domain.dto.ChildDTO;
import com.codeboogie.kidmapbackend.common.member.domain.dto.NotifyDTO;
import com.codeboogie.kidmapbackend.common.member.domain.model.Child;
import com.codeboogie.kidmapbackend.common.member.domain.model.Notify;

import java.util.List;

public interface NotifyService {

    void registerNotify(NotifyDTO notifyDTO);    // 위험지역 저장하기

    List<Notify> fetchNotify();     // 신고된 위험지역 가져오기
}