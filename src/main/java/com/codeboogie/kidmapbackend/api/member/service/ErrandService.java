package com.codeboogie.kidmapbackend.api.member.service;

import com.codeboogie.kidmapbackend.common.member.domain.dto.ErrandDTO;
import com.codeboogie.kidmapbackend.common.member.domain.dto.MemberDTO;

public interface ErrandService {

    void registerErrand(ErrandDTO errandDTO);   // 심부름 설정하기

}
