package com.codeboogie.kidmapbackend.api.member.service;

import com.codeboogie.kidmapbackend.common.member.domain.dto.MemberDTO;

import java.text.ParseException;

public interface MemberService {
    boolean createMember(MemberDTO memberDTO);

    void updateMember(MemberDTO memberDTO);

    Integer getchildNum(MemberDTO memberDTO);
}
