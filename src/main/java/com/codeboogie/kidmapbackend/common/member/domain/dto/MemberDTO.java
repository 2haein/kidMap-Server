package com.codeboogie.kidmapbackend.common.member.domain.dto;

import com.codeboogie.kidmapbackend.common.member.domain.model.Child;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MemberDTO {
    private String _id;

    private String userId;

    private String userName;

    private String telNum;  // 부모 전화번호

    private Integer childNum;

    private List<Child> Child = new ArrayList<Child>();
}
