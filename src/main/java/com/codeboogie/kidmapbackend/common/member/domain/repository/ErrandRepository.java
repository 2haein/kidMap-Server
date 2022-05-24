package com.codeboogie.kidmapbackend.common.member.domain.repository;

import com.codeboogie.kidmapbackend.common.member.domain.model.Errand;
import com.codeboogie.kidmapbackend.common.member.domain.model.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ErrandRepository extends MongoRepository<Errand, String> {
     Errand findByUUID(String UUID);

     List<Errand> findAllByUserId(String UserId);
}
