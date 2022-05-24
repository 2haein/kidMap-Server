package com.codeboogie.kidmapbackend.api.member.service;

import com.codeboogie.kidmapbackend.common.member.domain.dto.ErrandDTO;
import com.codeboogie.kidmapbackend.common.member.domain.dto.MemberDTO;
import com.codeboogie.kidmapbackend.common.member.domain.model.Errand;
import com.codeboogie.kidmapbackend.common.member.domain.model.Member;
import com.codeboogie.kidmapbackend.common.member.domain.repository.ErrandRepository;
import com.codeboogie.kidmapbackend.common.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ErrandServiceImpl implements ErrandService{

    @Autowired
    private final ErrandRepository errandRepository;

    @Autowired
    private final MemberRepository memberRepository;

    @Autowired
    private MongoTemplate mongoTemplate; //몽고DB 템플릿 불러오기

    @Override
    public void registerErrand(final ErrandDTO errandDTO){
      try{
//        if(memberDTO == null) {
//            throw new NullPointerException("Data Null");
//        }
        Errand errand = new Errand();
        errand.setUserId(errandDTO.getUserId());
        errand.setUUID(errandDTO.getUUID());
        errand.setE_date(errandDTO.getE_date());
        errand.setE_content(errandDTO.getE_content());
        errand.setTarget_latitude(errandDTO.getTarget_latitude());
        errand.setTarget_longitude(errandDTO.getTarget_longitude());
        errand.setTarget_name(errandDTO.getTarget_name());
        errand.setStart_latitude(errandDTO.getStart_latitude());
        errand.setStart_longitude(errandDTO.getStart_longitude());
        errand.setStart_name(errandDTO.getStart_name());
        errand.setChecking(errandDTO.isChecking());
        errand.setQuest(errandDTO.getQuest());

        System.out.println("안드로이드 -> 서버 ServiceImpl registerErrand 업데이트:"+ errandDTO.getE_date()+errandDTO.getE_content());

        errandRepository.save(errand);

        /**
         * 등록한 심부름 Child collection에 업데이트하기*/
        Query query = new Query(Criteria.where("UUID").is(errandDTO.getUUID()));

        List<Errand> errandList = new ArrayList<>();
        errandList.add(errand);

        Update update = new Update();
        update.push("Errand", errand);

        mongoTemplate.updateFirst(query, update, "child");


    } catch (Exception e){
        System.out.println("심부름 정보 저장 오류......");
        e.printStackTrace();
    }
    }

    // 기존 디폴트 심부름 완료여부 true이고 심부름 등록시 심부름 완료여부는 false이다.
    // 완료시 true값으로 완료 업데이트 하기
    public void updateErrandChecking(MemberDTO memberDTO){
        System.out.println("안드로이드 -> 서버 ServiceImpl updateChecking 업데이트:"+ memberDTO.isErrandComplete());
        Criteria criteria = new Criteria();
        Query query = new Query(Criteria.where("userId").is(memberDTO.getUserId()));

//        List<Errand> errandList = new ArrayList<>();
//        errandList.add(errand);

        Update update = new Update();

//        Criteria criteria_arr[] = new Criteria[2];
        update.set("isErrandComplete", memberDTO.isErrandComplete());


        mongoTemplate.updateFirst(query, update, "member");
    }

    public boolean fetchErrandChecking(MemberDTO memberDTO){
//        Member member = new Member();
//        member.setUserId(memberDTO.getUserId());
        System.out.println("안드로이드 -> 서버 ServiceImpl fetchErrandChecking 실행");

        Member member = memberRepository.findByUserId(memberDTO.getUserId());
        System.out.println("심부름 완료여부 가져오기: " + member.isErrandComplete());


        return member.isErrandComplete();

    }

    // 최근 부모 심부름 DB정보 가져오기
    public Errand fetchRecentErrand(String userId){

        Query query = new Query();

        Criteria criteria = new Criteria();

//        Criteria criteria_arr[] = new Criteria[2];
        criteria = Criteria.where("userId").is(userId);
        query.addCriteria(criteria);
//        criteria_arr[0] = Criteria.where("uuid").is(uuid);
//        criteria_arr[1] = Criteria.where("uuid").is(uuid);
//        errandRepository.  find().sort({_id:1});
//        mongoOperation.find(query, Domain.class);

//        query.addCriteria(criteria.andOperator(criteria_arr));
//        System.out.println("123"+mongoTemplate.findOne(query, Child.class, "child"));
        List<Errand> entityList = errandRepository.findAllByUserId(userId);

        return entityList.get(entityList.size() - 1);
    }



}
