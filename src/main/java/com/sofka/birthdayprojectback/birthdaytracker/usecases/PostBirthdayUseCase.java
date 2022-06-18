package com.sofka.birthdayprojectback.birthdaytracker.usecases;

import com.sofka.birthdayprojectback.birthdaytracker.dto.BirthdayDTO;
import com.sofka.birthdayprojectback.birthdaytracker.mapper.BirthdayMapper;
import com.sofka.birthdayprojectback.birthdaytracker.repository.BirthdayRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Service
@Validated
public class PostBirthdayUseCase {
    private final BirthdayMapper mapper;
    private final BirthdayRepository repository;

    public PostBirthdayUseCase(BirthdayMapper mapper, BirthdayRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public Mono<BirthdayDTO> apply(@Valid BirthdayDTO dto) {
        return null;
    }

}
