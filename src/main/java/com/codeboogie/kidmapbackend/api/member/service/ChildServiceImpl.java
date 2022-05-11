package com.codeboogie.kidmapbackend.api.member.service;

import com.codeboogie.kidmapbackend.common.member.domain.dto.ChildDTO;
import com.codeboogie.kidmapbackend.common.member.domain.dto.MemberDTO;
import com.codeboogie.kidmapbackend.common.member.domain.model.Child;
import com.codeboogie.kidmapbackend.common.member.domain.model.Member;
import com.codeboogie.kidmapbackend.common.member.domain.repository.ChildRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class ChildServiceImpl implements ChildService{

    @Autowired
    private final ChildRepository childRepository;

    @Autowired
    private MongoTemplate mongoTemplate; //몽고DB 템플릿 불러오기


    @Override
    public boolean findUUID(ChildDTO childDTO){
        try{

            Child child = childRepository.findByUUID(childDTO.getUUID());
            System.out.println("ServiceImpl/findUUID: " + child);

            if (child == null) {
                return false;
            }

        } catch (Exception e){
            System.out.println("findUUID 오류발생......");
            e.printStackTrace();
        }
        return true;
    }


    public Child fetchChild(String uuid) {

        Query query = new Query();
        Criteria criteria = new Criteria();

//        Criteria criteria_arr[] = new Criteria[2];
        criteria = Criteria.where("UUID").is(uuid);
        query.addCriteria(criteria);
//        criteria_arr[0] = Criteria.where("uuid").is(uuid);
//        criteria_arr[1] = Criteria.where("uuid").is(uuid);

//        query.addCriteria(criteria.andOperator(criteria_arr));
//        System.out.println("123"+mongoTemplate.findOne(query, Child.class, "child"));

        return mongoTemplate.findOne(query, Child.class, "child");
    }


    // 아이 현재 위치 저장하
    @Override
    public void savePositionChild(String uuid){
//        if(uuid == null) {
//            throw new NullPointerException("Data Null");
//        }
        Member kidmapMember = new Member();
        kidmapMember.setUserId(memberDTO.getUserId());
        kidmapMember.setTelNum(memberDTO.getTelNum());

        System.out.println("안드로이드 -> 서버 ServiceImpl registerTelNum 업데이트:"+ memberDTO.getTelNum());
        Query query = new Query(Criteria.where("uuid").is(memberDTO.getUserId()));


        //SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        //Date sDate = inputFormat.parse(feeling.getPublishDate().toString());

        Update update = new Update();
        update.set("telNum", kidmapMember.getTelNum());


        mongoTemplate.updateFirst(query, update, "member");
    }

}
