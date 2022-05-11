package com.codeboogie.kidmapbackend.api.member.controller;

import com.codeboogie.kidmapbackend.api.member.service.MemberService;
import com.codeboogie.kidmapbackend.api.member.service.NotifyService;
import com.codeboogie.kidmapbackend.common.member.domain.dto.MemberDTO;
import com.codeboogie.kidmapbackend.common.member.domain.dto.NotifyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("api")
public class NotifyController {

    @Autowired
    private NotifyService notifyService;

    @RequestMapping(value = "/registerNotify", method = {RequestMethod.POST})
    public @ResponseBody void registerNotify(@RequestParam HashMap<String, String> data, NotifyDTO notifyDTO) {
        System.out.println("안드로이드 -> 서버 /notifyDanger" + data + ":" + data.get("userId"));

        try {

            String userId = String.valueOf(data.get("userId"));
            Double notify_latitude = Double.valueOf((data.get("notify_latitude")));
            Double notify_longitude = Double.valueOf((data.get("notify_longitude")));
            String notify_name = String.valueOf(data.get("notify_name"));
            String notify_content = String.valueOf(data.get("notify_content"));
            notifyDTO.setUserId(userId);
            notifyDTO.setNotify_latitude(notify_latitude);
            notifyDTO.setNotify_longitude(notify_longitude);
            notifyDTO.setNotify_name(notify_name);
            notifyDTO.setNotify_content(notify_content);

            notifyService.registerNotify(notifyDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}