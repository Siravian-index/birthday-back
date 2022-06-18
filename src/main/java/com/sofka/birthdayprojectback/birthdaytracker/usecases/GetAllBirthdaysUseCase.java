package com.sofka.birthdayprojectback.birthdaytracker.usecases;

import com.sofka.birthdayprojectback.birthdaytracker.dto.BirthdayDTO;
import com.sofka.birthdayprojectback.birthdaytracker.mapper.BirthdayMapper;
import com.sofka.birthdayprojectback.birthdaytracker.repository.BirthdayRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GetAllBirthdaysUseCase {

    private final BirthdayMapper mapper;
    private final BirthdayRepository repository;

    public GetAllBirthdaysUseCase(BirthdayMapper mapper, BirthdayRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public Flux<BirthdayDTO> apply() {
        return repository.findAll().map(entity -> {
            BirthdayDTO birthdayDTO = mapper.entityToDTO(entity);
            birthdayDTO.setSecret("Shhh it's a secret");
            return birthdayDTO;
        });
    }


}
