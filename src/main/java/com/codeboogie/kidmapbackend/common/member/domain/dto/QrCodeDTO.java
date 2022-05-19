package com.codeboogie.kidmapbackend.common.member.domain.dto;

import lombok.Data;

@Data
public class QrCodeDTO {
    private double home_latitude;   // 아이 집 위치 위도

    private double home_longitude;   // 아이 집 위치 경도

    private String home_address;   // 아이 집 위치 경도

    private boolean agreement;  // 개인정보 이용 동의 사항
}
