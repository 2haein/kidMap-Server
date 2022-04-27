package com.codeboogie.kidmapbackend.api.member.service;

import com.codeboogie.kidmapbackend.common.member.domain.dto.MemberDTO;
import com.codeboogie.kidmapbackend.common.member.domain.model.Member;

import java.text.ParseException;

public interface MemberService {
    boolean createMember(MemberDTO memberDTO);

    void registerChild(MemberDTO memberDTO);

    Integer fetchChildNum(MemberDTO memberDTO);

    void createUUID(int key, MemberDTO memberDTO);     //UUID값 생성하기

    void registerTelNum(MemberDTO memberDTO);   // 부모 전화번호 등록하기


}
