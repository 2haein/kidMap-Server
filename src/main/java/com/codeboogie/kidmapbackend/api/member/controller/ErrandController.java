package com.codeboogie.kidmapbackend.api.member.controller;

import com.codeboogie.kidmapbackend.api.member.service.ErrandService;
import com.codeboogie.kidmapbackend.api.member.service.MemberService;
import com.codeboogie.kidmapbackend.common.member.domain.dto.ErrandDTO;
import com.codeboogie.kidmapbackend.common.member.domain.dto.MemberDTO;
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

            String E_content = String.valueOf(data.get("E_content"));
            String UUID = String.valueOf(data.get("UUID"));
            Double target_latitude = Double.valueOf((data.get("target_latitude")));
            Double target_longitude = Double.valueOf((data.get("target_longitude")));
            String target_name = String.valueOf(data.get("target_name"));
            Double start_latitude = Double.valueOf((data.get("start_latitude")));
            Double start_longitude = Double.valueOf((data.get("start_longitude")));
            String start_name = String.valueOf(data.get("start_name"));
            boolean checking = Boolean.parseBoolean(data.get("checking"));
            List<String> quest = new ArrayList<String>();
            quest = Arrays.asList((data.get("quest")));

            errandDTO.setUUID(UUID);
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

}
