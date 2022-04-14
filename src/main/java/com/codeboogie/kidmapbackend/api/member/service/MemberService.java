package com.codeboogie.kidmapbackend.api.member.service;

import com.codeboogie.kidmapbackend.common.member.domain.dto.MemberDTO;

import java.text.ParseException;

public interface MemberService {
    boolean createMember(MemberDTO memberDTO);

    void registerChild(MemberDTO memberDTO);

    Integer fetchChildNum(MemberDTO memberDTO);

    //UUID값 생성하기
    void createUUID(int key, MemberDTO memberDTO);
}
