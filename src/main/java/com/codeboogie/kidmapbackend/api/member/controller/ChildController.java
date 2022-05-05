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
}