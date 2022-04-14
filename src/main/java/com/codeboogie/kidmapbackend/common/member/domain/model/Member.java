package com.codeboogie.kidmapbackend.common.member.domain.model;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 이해인
 * @version 1.0, 2022.03.25 생성
 * 회원 API 객체 모델 정의
 *
 */

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "member")
public class Member {

    @Id
    @Column(name= "member_id")  // mongodb에선 jpa적용 불가
    private String _id;

    @Indexed(unique = true)
    private String userId; //카카오 사용자 아이디

    private String userName; //카카오 사용자 이름

    private Integer childNum;   // 자녀수

    private List<UUID> UUID = new ArrayList<UUID>();  // 자녀 UUID

}
