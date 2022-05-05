package com.codeboogie.kidmapbackend.common.member.domain.repository;

import com.codeboogie.kidmapbackend.common.member.domain.model.Child;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChildRepository extends MongoRepository<Child, String> {
    Child findByUUID(String UUID);
}

