package com.sofka.birthdayprojectback.birthdaytracker.usecases;

import com.sofka.birthdayprojectback.birthdaytracker.bcrypt.BcryptMapper;
import com.sofka.birthdayprojectback.birthdaytracker.dto.BirthdayDTO;
import com.sofka.birthdayprojectback.birthdaytracker.mapper.BirthdayMapper;
import com.sofka.birthdayprojectback.birthdaytracker.repository.BirthdayRepository;
import com.sofka.birthdayprojectback.birthdaytracker.util.DateFormatter;
import com.sofka.birthdayprojectback.config.BcryptConfig;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Service
@Validated
public class PostBirthdayUseCase {
    private final BirthdayMapper mapper;
    private final BirthdayRepository repository;
    private final DateFormatter dateFormatter;
    private final BcryptMapper bcrypt;


    public static void main(String[] args) {
    }
    public PostBirthdayUseCase(BirthdayMapper mapper, BirthdayRepository repository, DateFormatter dateFormatter, BcryptConfig bcrypt, BcryptMapper bcrypt1) {
        this.mapper = mapper;
        this.repository = repository;
        this.dateFormatter = dateFormatter;
        this.bcrypt = bcrypt1;
    }

    public Mono<BirthdayDTO> apply(@Valid BirthdayDTO dto) {
        dateFormatter.setUserAgeFromDate(dto);
        dto.setSecret(bcrypt.encode(dto.getSecret()));
        return repository.save(mapper.dtoToEntity(dto)).map(mapper::entityToDTO);
    }


}
