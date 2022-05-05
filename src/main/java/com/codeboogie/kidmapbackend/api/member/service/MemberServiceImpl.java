package com.codeboogie.kidmapbackend.api.member.service;

import com.codeboogie.kidmapbackend.common.member.domain.dto.ChildDTO;
import com.codeboogie.kidmapbackend.common.member.domain.dto.MemberDTO;
import com.codeboogie.kidmapbackend.common.member.domain.model.Child;
import com.codeboogie.kidmapbackend.common.member.domain.model.Member;
import com.codeboogie.kidmapbackend.common.member.domain.repository.ChildRepository;
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
    private final ChildRepository childRepository;

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

    /**
     *  자녀 등록 시 자녀테이블에 각각의 UUID에 맞게 레코드 생성
     * */
    @Override
    public void createUUID(int key, MemberDTO memberDTO, ChildDTO childDTO) {

        System.out.println("안드로이드 -> 서버 ServiceImpl createUUID 실행");

        RandomString rs = new RandomString(10);
        String uuidValue = rs.nextString();
        System.out.println("UUID 랜덤 값 확인 key-" + key +": " + uuidValue);

        Member member = memberRepository.findByUserId(memberDTO.getUserId());

        List<Child> arrayChild = new ArrayList<>();
        Child item = new Child();
        item.setKey(key);
        item.setUUID(uuidValue);
        item.setChildName(member.getUserName()+"자녀"+(key+1));
        arrayChild.add(item);

        Update update = new Update();
        update.push("Child").each(arrayChild);
        Query query = new Query(Criteria.where("userId").is(memberDTO.getUserId()));

        mongoTemplate.updateFirst(query, update, "member");

        childDTO.setKey(key);
        childDTO.setParent_name(member.getUserName());
        childDTO.setParent_id(memberDTO.getUserId());
        childDTO.setUUID(uuidValue);
        childDTO.setChildName(member.getUserName()+"자녀"+(key+1));

        Child child = new Child();
        child.setKey(childDTO.getKey());
        child.setParent_id(childDTO.getParent_id());
        child.setParent_name(childDTO.getParent_name());
        child.setChildName(childDTO.getChildName());
        child.setUUID(childDTO.getUUID());

        childRepository.save(child);
    }

    // 부모 전화번호 등록하기
    @Override
    public void registerTelNum(final MemberDTO memberDTO){
//        if(memberDTO == null) {
//            throw new NullPointerException("Data Null");
//        }
        Member kidmapMember = new Member();
        kidmapMember.setUserId(memberDTO.getUserId());
        kidmapMember.setTelNum(memberDTO.getTelNum());

        System.out.println("안드로이드 -> 서버 ServiceImpl registerTelNum 업데이트:"+ memberDTO.getTelNum());
        Query query = new Query(Criteria.where("userId").is(memberDTO.getUserId()));


        //SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        //Date sDate = inputFormat.parse(feeling.getPublishDate().toString());

        Update update = new Update();
        update.set("telNum", kidmapMember.getTelNum());


        mongoTemplate.updateFirst(query, update, "member");
    }

    @Override
    public String fetchTelNum(MemberDTO memberDTO) {
        Member kidmapMember = new Member();
        kidmapMember.setUserId(memberDTO.getUserId());
        System.out.println("안드로이드 -> 서버 ServiceImpl fetchTelNum 실행");

        Member member = memberRepository.findByUserId(memberDTO.getUserId());
        System.out.println("등록한 전화번호 가져오기: " + member.getTelNum());


        return member.getTelNum();
    }

}
