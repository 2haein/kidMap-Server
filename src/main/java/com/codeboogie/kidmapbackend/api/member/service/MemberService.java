package com.codeboogie.kidmapbackend.api.member.service;

import com.codeboogie.kidmapbackend.common.member.domain.dto.MemberDTO;

public interface MemberService {
    boolean createMember(MemberDTO memberDTO);
}
