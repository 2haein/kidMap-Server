package com.codeboogie.kidmapbackend.api.member.service;

import com.codeboogie.kidmapbackend.common.member.domain.dto.ChildDTO;
import com.codeboogie.kidmapbackend.common.member.domain.dto.NotifyDTO;
import com.codeboogie.kidmapbackend.common.member.domain.model.Child;

public interface NotifyService {

    void registerNotify(NotifyDTO notifyDTO);    // 위험지역 저장하기
}