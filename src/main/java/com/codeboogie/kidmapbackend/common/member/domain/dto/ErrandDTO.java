package com.codeboogie.kidmapbackend.common.member.domain.dto;

import com.codeboogie.kidmapbackend.common.member.domain.model.Child;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ErrandDTO {
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

    private boolean checking;  //   위험요소 파악했는지 여부

    private List<String> quest; // 퀘스트 목록
}
