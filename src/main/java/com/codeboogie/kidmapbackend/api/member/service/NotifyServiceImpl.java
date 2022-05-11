package com.codeboogie.kidmapbackend.api.member.service;

import com.codeboogie.kidmapbackend.common.member.domain.dto.ChildDTO;
import com.codeboogie.kidmapbackend.common.member.domain.dto.NotifyDTO;
import com.codeboogie.kidmapbackend.common.member.domain.model.Child;
import com.codeboogie.kidmapbackend.common.member.domain.model.Notify;
import com.codeboogie.kidmapbackend.common.member.domain.repository.ChildRepository;
import com.codeboogie.kidmapbackend.common.member.domain.repository.NotifyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotifyServiceImpl implements NotifyService{

    @Autowired
    private final NotifyRepository notifyRepository;

    @Autowired
    private MongoTemplate mongoTemplate; //몽고DB 템플릿 불러오기


    @Override
    public void registerNotify(NotifyDTO notifyDTO){
        try{
            Notify notify = new Notify();
            notify.setUserId(notifyDTO.getUserId());
            notify.setNotify_name(notifyDTO.getNotify_name());
            notify.setNotify_content(notifyDTO.getNotify_content());
            notify.setNotify_latitude(notifyDTO.getNotify_latitude());
            notify.setNotify_longitude(notifyDTO.getNotify_longitude());

            notifyRepository.save(notify);

            System.out.println("ServiceImpl/registerNotify: " + notify);


        } catch (Exception e){
            System.out.println("registerNotify 오류발생......");
            e.printStackTrace();
        }
    }

}
