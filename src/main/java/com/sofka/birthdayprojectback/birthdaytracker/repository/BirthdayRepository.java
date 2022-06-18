package com.sofka.birthdayprojectback.birthdaytracker.repository;

import com.sofka.birthdayprojectback.birthdaytracker.document.BirthdayEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BirthdayRepository extends ReactiveMongoRepository<BirthdayEntity, String> {

}
