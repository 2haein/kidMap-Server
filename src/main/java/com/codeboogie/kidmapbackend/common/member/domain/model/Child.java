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
 * @version 1.0, 2022.05.03 생성
 * 아이 API 객체 모델 정의
 *
 */
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "child")
public class Child {

    @Id
    private String _id;

    private String  parent_id;  // 부모 아이디

    private String parent_name; // 부모 이름

    private int key;

    @Indexed(unique = true)
    private String UUID; //자녀 아이디

    private String childName; // 자녀 이름

    private double current_latitude;   // 아이 현재 위치 위도

    private double current_longitude;   // 아이 현재 위치 경도

    private List<Errand> Errand = new ArrayList<Errand>(); // 각 자녀별 심부름 목록
}
