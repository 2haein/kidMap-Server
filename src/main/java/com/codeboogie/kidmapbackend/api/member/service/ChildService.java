package com.codeboogie.kidmapbackend.api.member.service;

import com.codeboogie.kidmapbackend.common.member.domain.dto.ChildDTO;

public interface ChildService {

    boolean findUUID(ChildDTO childDTO);    // 아이 UUID맞는지 확인하고 아이 이름 가져오기

}