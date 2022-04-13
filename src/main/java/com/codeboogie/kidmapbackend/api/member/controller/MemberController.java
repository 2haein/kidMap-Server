package com.codeboogie.kidmapbackend.api.member.controller;

import com.codeboogie.kidmapbackend.api.member.service.MemberService;
import com.codeboogie.kidmapbackend.common.member.domain.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("api")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/member", method= {RequestMethod.POST})
    public @ResponseBody void insert(@RequestParam HashMap<String, String> data, MemberDTO memberDTO) {
        System.out.println("안드로이드 -> 서버 /member"+ data+ ":"+ data.get("userId"));

        try{

            String userId = String.valueOf(data.get("userId"));
            String userName = String.valueOf(data.get("userName"));
            memberDTO.setUserId(userId);
            memberDTO.setUserName(userName);

            memberService.createMember(memberDTO);

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @RequestMapping(path="/registerChild", method={ RequestMethod.GET, RequestMethod.POST })
    public @ResponseBody void updateMember(@RequestBody HashMap<String, String> data, MemberDTO memberDTO) {
        System.out.println("안드로이드 -> 서버 /registerChild"+ data+ ":"+ data.get("childNum") + " , " +data.get("userId"));

        try {

            Integer childNum = Integer.parseInt(data.get("childNum"));
            String userId = String.valueOf(data.get("userId"));
            memberDTO.setChildNum(childNum);
            memberDTO.setUserId(userId);

            // UUID 랜덤 고유 값 생성
            UUID random = UUID.randomUUID();
            System.out.println("랜덤 고유값 : " +random);

            memberService.updateMember(memberDTO);

        } catch(final Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(path="/fetchChildNum", method={ RequestMethod.GET, RequestMethod.POST })
    public @ResponseBody Integer fetchChildNum(@RequestBody HashMap<String, String> data, MemberDTO memberDTO) {
        System.out.println("안드로이드 -> 서버 /fetchChildNum"+ data+ ":"+ data.get("userId"));

        try {
//            Integer childNum = Integer.parseInt(data.get("childNum"));
            String userId = String.valueOf(data.get("userId"));
            memberDTO.setUserId(userId);

            return memberService.getchildNum(memberDTO);

        } catch(final Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
