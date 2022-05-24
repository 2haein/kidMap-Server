package com.codeboogie.kidmapbackend.common.member.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 이해인
 * @version 1.0, 2022.04.28 생성
 * 심부름 API 객체 모델 정의
 *
 */

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "errand")
public class Errand {

    @Id
//    @Column(name= "member_id")  // mongodb에선 jpa적용 불가
    private String _id;

    private String UUID; //자녀 아이디

    private Date E_date; //심부름 일자

    private String E_content;  // 심부름 내용

    private double target_latitude;   // 목표 장소 위도

    private double target_longitude;   // 목표 장소 경도

    private String target_name; // 목표 장소 이름

    private double start_latitude;   // 출발 장소 위도

    private double start_longitude;   // 출발 장소 경도

    private String start_name; // 출발지 장소 이름

    private boolean checking;  //   심부름 완료 여부

    private List<String> quest; // 퀘스트 목록
}
