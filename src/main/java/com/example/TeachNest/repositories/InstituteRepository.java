package com.example.TeachNest.repositories;

import com.example.TeachNest.entities.Institute;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InstituteRepository extends MongoRepository<Institute, String> {
}
