package com.sofka.birthdayprojectback.birthdaytracker.usecases;

import com.sofka.birthdayprojectback.birthdaytracker.dto.BirthdayDTO;
import com.sofka.birthdayprojectback.birthdaytracker.mapper.BirthdayMapper;
import com.sofka.birthdayprojectback.birthdaytracker.repository.BirthdayRepository;
import com.sofka.birthdayprojectback.birthdaytracker.util.DateFormatter;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Service
public class PutBirthdayUseCase {

    private final BirthdayMapper mapper;
    private final BirthdayRepository repository;
    private final DateFormatter dateFormatter;


    public static void main(String[] args) {
    }
    public PutBirthdayUseCase(BirthdayMapper mapper, BirthdayRepository repository, DateFormatter dateFormatter) {
        this.mapper = mapper;
        this.repository = repository;
        this.dateFormatter = dateFormatter;
    }

    public Mono<BirthdayDTO> apply(@Valid BirthdayDTO dto) {
        dateFormatter.setUserAgeFromDate(dto);
        return repository.save(mapper.dtoToEntity(dto)).map(mapper::entityToDTO);
    }


}
