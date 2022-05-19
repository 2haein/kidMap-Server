package com.codeboogie.kidmapbackend.api.member.controller;

import com.codeboogie.kidmapbackend.api.member.service.ChildService;
import com.codeboogie.kidmapbackend.api.member.service.MemberService;
import com.codeboogie.kidmapbackend.common.member.domain.dto.ChildDTO;
import com.codeboogie.kidmapbackend.common.member.domain.dto.MemberDTO;
import com.codeboogie.kidmapbackend.common.member.domain.model.Child;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("api")
public class ChildController {

    @Autowired
    private ChildService childService;

    // 아이 UUID로그인 시 식별 여부, 인지 아닌지
    @RequestMapping(path = "/findUUID", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody boolean fetchUUID(@RequestBody HashMap<String, String> data, ChildDTO childDTO) {
        System.out.println("안드로이드 -> 서버 /findUUID " + data + ":" + data.get("UUID"));

        try {
            String uuid = String.valueOf(data.get("UUID"));
            childDTO.setUUID(uuid);

            return childService.findUUID(childDTO);

        } catch (final Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 아이 UUID를 통해 아이 정보 가져오기
    @RequestMapping(path = "/fetchChild", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    Child fetchChild(@RequestBody HashMap<String, String> data, ChildDTO childDTO) {
        System.out.println("안드로이드 -> 서버 /fetchChild " + data + ":" + data.get("UUID"));

        String uuid = String.valueOf(data.get("UUID"));
//            childDTO.setUUID(uuid);

        return childService.fetchChild(uuid);

    }

    // 아이 현재 위치 저장하기
    @RequestMapping(path = "/savePositionChild", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    void savePositionChild(@RequestBody HashMap<String, String> data) {
        System.out.println("안드로이드 -> 서버 /savePositionChild " + data + ":" + data.get("UUID"));

        String uuid = String.valueOf(data.get("UUID"));
        Double current_latitude = Double.valueOf((data.get("current_latitude")));   // 아이 현재 위치 위도
        Double current_longitude = Double.valueOf((data.get("current_longitude")));     // 아이 현재 위치 경도

//            childDTO.setUUID(uuid);

        childService.savePositionChild(uuid, current_latitude, current_longitude);

    }

    // 아이 QR 정보 저장하기
    @RequestMapping(path = "/saveQRCodeChild", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    void saveQRCodeChild(@RequestBody HashMap<String, String> data) {
        System.out.println("안드로이드 -> 서버 /saveQRCodeChild " + data + ":" + data.get("UUID"));

        String uuid = String.valueOf(data.get("UUID"));
        Double home_latitude = Double.valueOf((data.get("home_latitude")));   // 아이 집 위치 위도
        Double home_longitude = Double.valueOf((data.get("home_longitude")));     // 아이 집 위치 경도
        String home_address = String.valueOf(data.get("home_address"));
        boolean agreement = Boolean.parseBoolean(data.get("agreement"));

//            childDTO.setUUID(uuid);

        childService.saveQRCodeChild(uuid, home_latitude, home_longitude, home_address, agreement);

    }
}