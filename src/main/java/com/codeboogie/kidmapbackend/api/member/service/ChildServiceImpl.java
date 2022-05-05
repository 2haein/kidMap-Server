package com.codeboogie.kidmapbackend.api.member.service;

import com.codeboogie.kidmapbackend.common.member.domain.dto.ChildDTO;
import com.codeboogie.kidmapbackend.common.member.domain.dto.MemberDTO;
import com.codeboogie.kidmapbackend.common.member.domain.model.Child;
import com.codeboogie.kidmapbackend.common.member.domain.model.Member;
import com.codeboogie.kidmapbackend.common.member.domain.repository.ChildRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChildServiceImpl implements ChildService{

    @Autowired
    private final ChildRepository childRepository;

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
}
