package com.sofka.birthdayprojectback.birthdaytracker.usecases;

import com.sofka.birthdayprojectback.birthdaytracker.dto.BirthdayDTO;
import com.sofka.birthdayprojectback.birthdaytracker.mapper.BirthdayMapper;
import com.sofka.birthdayprojectback.birthdaytracker.repository.BirthdayRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Period;

@Service
@Validated
public class PostBirthdayUseCase {
    private final BirthdayMapper mapper;
    private final BirthdayRepository repository;

    public static void main(String[] args) {
    }
    public PostBirthdayUseCase(BirthdayMapper mapper, BirthdayRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public Mono<BirthdayDTO> apply(@Valid BirthdayDTO dto) {
        setUserAgeFromDate(dto);
        return repository.save(mapper.dtoToEntity(dto)).map(mapper::entityToDTO);
    }

    private void setUserAgeFromDate(BirthdayDTO dto) {
        String[] split = dto.getBirthday().split("-");
        int day = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int year = Integer.parseInt(split[2]);

        LocalDate userDate = LocalDate.of(year, month, day);
        Period between= Period.between(userDate, LocalDate.now());
        int age = between.getYears();
        dto.setAge("" + age);
    }

}
