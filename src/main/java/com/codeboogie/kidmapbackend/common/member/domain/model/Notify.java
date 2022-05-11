package com.codeboogie.kidmapbackend.common.member.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 이해인
 * @version 1.0, 2022.05.11 생성
 * 위험지역 API 객체 모델 정의
 *
 */
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "notify")
public class Notify {

    @Id
    private String _id;

//    @Indexed(unique = true)
    private String userId; //카카오 사용자 아이디

    private double notify_latitude;  // 위험지역 위도

    private double notify_longitude;  // 위험지역 경도

    private String notify_name; // 위험지역 주소 이름

    private String notify_content; // 위험내용

}
