package com.codeboogie.kidmapbackend.common.member.domain.dto;

import com.codeboogie.kidmapbackend.common.member.domain.model.Errand;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChildDTO {
    private String _id;

    private String parent_id;  // 부모 아이디

    private String parent_name; // 부모 이름

    private int key;

    private String UUID; // 자녀 고유 식별자

    private String childName; // 자녀 이름

    private double current_latitude;   // 아이 현재 위치 위도

    private double current_longitude;   // 아이 현재 위치 경도

    private List<Errand> Errand = new ArrayList<Errand>(); // 각 자녀별 심부름 목록
}
