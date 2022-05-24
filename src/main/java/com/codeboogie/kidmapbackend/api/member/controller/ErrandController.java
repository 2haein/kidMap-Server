package com.codeboogie.kidmapbackend.api.member.controller;

import com.codeboogie.kidmapbackend.api.member.service.ErrandService;
import com.codeboogie.kidmapbackend.api.member.service.MemberService;
import com.codeboogie.kidmapbackend.common.member.domain.dto.ChildDTO;
import com.codeboogie.kidmapbackend.common.member.domain.dto.ErrandDTO;
import com.codeboogie.kidmapbackend.common.member.domain.dto.MemberDTO;
import com.codeboogie.kidmapbackend.common.member.domain.model.Child;
import com.codeboogie.kidmapbackend.common.member.domain.model.Errand;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("api")
public class ErrandController {

    @Autowired
    private ErrandService errandService;

    @RequestMapping(path="/registerErrand", method={ RequestMethod.GET, RequestMethod.POST })
    public @ResponseBody void registerErrand(@RequestBody HashMap<String, String> data, ErrandDTO errandDTO) {
        System.out.println("안드로이드 -> 서버 /registerErrand"+ data+ ":"+ data.get("E_content") + " , " +data.get("UUID"));

        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            String errandDate = data.get("E_date") + ":00.000Z";
            Date E_date = inputFormat.parse(errandDate);

            String userId = String.valueOf(data.get("userId"));
            String E_content = String.valueOf(data.get("E_content"));
            String UUID = String.valueOf(data.get("UUID"));
            Double target_latitude = Double.valueOf((data.get("target_latitude")));
            Double target_longitude = Double.valueOf((data.get("target_longitude")));
            String target_name = String.valueOf(data.get("target_name"));
            Double start_latitude = Double.valueOf((data.get("start_latitude")));
            Double start_longitude = Double.valueOf((data.get("start_longitude")));
            String start_name = String.valueOf(data.get("start_name"));
            boolean checking = Boolean.parseBoolean(data.get("checking"));
            JSONParser json = new JSONParser();
            List<String> quest = null;

            if(data.get("quest") != null){
                JSONArray jsonArray = (JSONArray) json.parse(data.get("quest"));
                quest = jsonArray;
            }

            errandDTO.setUUID(UUID);
            errandDTO.setUserId(userId);
            errandDTO.setE_date(E_date);
            errandDTO.setE_content(E_content);
            errandDTO.setTarget_latitude(target_latitude);
            errandDTO.setTarget_longitude(target_longitude);
            errandDTO.setTarget_name(target_name);
            errandDTO.setStart_latitude(start_latitude);
            errandDTO.setStart_longitude(start_longitude);
            errandDTO.setStart_name(start_name);
            errandDTO.setChecking(checking);
            errandDTO.setQuest(quest);

            errandService.registerErrand(errandDTO);

        } catch(final Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(path="/updateErrandChecking", method={ RequestMethod.GET, RequestMethod.POST })
    public @ResponseBody void updateChecking(@RequestBody HashMap<String, String> data, MemberDTO memberDTO) {
        System.out.println("안드로이드 -> 서버 /updateErrandChecking"+ data+ ":"+ data.get("E_content") + " , " +data.get("userId"));

        try {
            boolean isErrandComplete = Boolean.parseBoolean(data.get("isErrandComplete"));
            String userId = String.valueOf(data.get("userId"));

            memberDTO.setErrandComplete(isErrandComplete);
            memberDTO.setUserId(userId);

            errandService.updateErrandChecking(memberDTO);

        } catch(final Exception e) {
            e.printStackTrace();
        }
    }

    // 심부름 완료 여부 확인하기
    @RequestMapping(path = "/fetchErrandChecking", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody boolean fetchErrandChecking(@RequestBody HashMap<String, String> data, MemberDTO memberDTO) {
        System.out.println("안드로이드 -> 서버 /fetchErrandChecking " + data + ":" + data.get("userId"));

        try {

//            boolean isErrandComplete = Boolean.parseBoolean(data.get("isErrandComplete"));
            String userId = String.valueOf(data.get("userId"));
            memberDTO.setUserId(userId);



        } catch (final Exception e) {
            e.printStackTrace();
        }

        return errandService.fetchErrandChecking(memberDTO);
    }

    // 부모가 시킨 최근 심부름 DB정보 가져오기
    @RequestMapping(path = "/fetchRecentErrand", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    Errand fetchRecentErrand(@RequestBody HashMap<String, String> data, ChildDTO childDTO) {
        System.out.println("안드로이드 -> 서버 /fetchRecentErrand " + data + ":" + data.get("userId"));

        String userId = String.valueOf(data.get("userId"));
//            childDTO.setUUID(uuid);

        return errandService.fetchRecentErrand(userId);

    }

}
