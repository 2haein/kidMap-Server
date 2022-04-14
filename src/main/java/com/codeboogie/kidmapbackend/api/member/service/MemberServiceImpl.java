package com.codeboogie.kidmapbackend.api.member.service;

import com.codeboogie.kidmapbackend.common.member.domain.dto.MemberDTO;
import com.codeboogie.kidmapbackend.common.member.domain.model.Member;
import com.codeboogie.kidmapbackend.common.member.domain.model.UUID;
import com.codeboogie.kidmapbackend.common.member.domain.repository.MemberRepository;
import com.codeboogie.kidmapbackend.util.RandomString;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    @Autowired
    private final MemberRepository memberRepository;

    @Autowired
    private MongoTemplate mongoTemplate; //몽고DB 템플릿 불러오기

    @Override
    public boolean createMember(MemberDTO memberDTO){
        try{
            Member kidmapMember = new Member();
            kidmapMember.setUserId(memberDTO.getUserId());
            kidmapMember.setUserName(memberDTO.getUserName());
            
            Member member = memberRepository.findByUserId(memberDTO.getUserId());
            System.out.println("createMember: " + member);

            if (member == null) {
                memberRepository.save(kidmapMember);
            }

        } catch (Exception e){
            System.out.println("이전에 가입한 사용자 로그인 중......");
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void registerChild(final MemberDTO memberDTO){
//        if(memberDTO == null) {
//            throw new NullPointerException("Data Null");
//        }
        Member kidmapMember = new Member();
        kidmapMember.setUserId(memberDTO.getUserId());
        kidmapMember.setChildNum(memberDTO.getChildNum());



        System.out.println("안드로이드 -> 서버 ServiceImpl registerChild 업데이트:"+ memberDTO.getChildNum());
        Query query = new Query(Criteria.where("userId").is(memberDTO.getUserId()));


        //SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        //Date sDate = inputFormat.parse(feeling.getPublishDate().toString());

        Update update = new Update();
        update.set("childNum", kidmapMember.getChildNum());


        mongoTemplate.updateFirst(query, update, "member");
    }

    @Override
    public Integer fetchChildNum(MemberDTO memberDTO) {
        Member kidmapMember = new Member();
        kidmapMember.setUserId(memberDTO.getUserId());
        System.out.println("안드로이드 -> 서버 ServiceImpl fetchChildNum 실행");

        Member member = memberRepository.findByUserId(memberDTO.getUserId());
        System.out.println("등록한 자녀수 가져오기: " + member.getChildNum());


        return member.getChildNum();
    }

    @Override
    public void createUUID(int key, MemberDTO memberDTO) {
        Member kidmapMember = new Member();
        kidmapMember.setUserId(memberDTO.getUserId());


        System.out.println("안드로이드 -> 서버 ServiceImpl createUUID 실행");

        RandomString rs = new RandomString(10);
        String uuidValue = rs.nextString();
        System.out.println("UUID 랜덤 값 확인 key-" + key +": " + uuidValue);

        Member member = memberRepository.findByUserId(kidmapMember.getUserId());

        List<UUID> arrayUUID = new ArrayList<>();
        UUID item = new UUID();
        item.key = key;
        item.UUID = uuidValue;
        arrayUUID.add(item);

        Update update = new Update();
        update.push("UUID").each(arrayUUID);
        Query query = new Query(Criteria.where("userId").is(memberDTO.getUserId()));

        mongoTemplate.updateFirst(query, update, "member");


    }
}
