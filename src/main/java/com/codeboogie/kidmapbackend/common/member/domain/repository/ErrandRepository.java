package com.codeboogie.kidmapbackend.common.member.domain.repository;

import com.codeboogie.kidmapbackend.common.member.domain.model.Errand;
import com.codeboogie.kidmapbackend.common.member.domain.model.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ErrandRepository extends MongoRepository<Errand, String> {
     Errand findByUUID(String UUID);
}
