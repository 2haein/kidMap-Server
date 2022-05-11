package com.codeboogie.kidmapbackend.common.member.domain.repository;

import com.codeboogie.kidmapbackend.common.member.domain.model.Child;
import com.codeboogie.kidmapbackend.common.member.domain.model.Member;
import com.codeboogie.kidmapbackend.common.member.domain.model.Notify;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotifyRepository extends MongoRepository<Notify, String> {
    Notify findByUserId(String userId);
}

