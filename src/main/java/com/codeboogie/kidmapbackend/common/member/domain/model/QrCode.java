package com.codeboogie.kidmapbackend.common.member.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author 이해인
 * @version 1.0, 2022.05.15 생성
 * qr API 객체 모델 정의
 *
 */
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "qrcode")
public class QrCode {

    private double home_latitude;   // 아이 집 위치 위도

    private double home_longitude;   // 아이 집 위치 경도

    private String home_address;   // 아이 집 위치 경도

    private boolean agreement;  // 개인정보 이용 동의 사항
}
