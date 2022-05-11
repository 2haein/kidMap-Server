package com.codeboogie.kidmapbackend.common.member.domain.dto;

import com.codeboogie.kidmapbackend.common.member.domain.model.Child;
import lombok.Data;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Data
public class NotifyDTO {
    private String _id;

    private String userId;

    private double notify_latitude;  // 위험지역 위도

    private double notify_longitude;  // 위험지역 경도

    private String notify_name; // 위험지역 주소 이름

    private String notify_content; // 위험내용

}
