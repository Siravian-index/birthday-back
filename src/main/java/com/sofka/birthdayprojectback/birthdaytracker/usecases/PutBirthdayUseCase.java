package com.sofka.birthdayprojectback.birthdaytracker.usecases;

import com.sofka.birthdayprojectback.birthdaytracker.bcrypt.BcryptMapper;
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
    private final BcryptMapper bcrypt;


    public static void main(String[] args) {
    }
    public PutBirthdayUseCase(BirthdayMapper mapper, BirthdayRepository repository, DateFormatter dateFormatter, BcryptMapper bcrypt) {
        this.mapper = mapper;
        this.repository = repository;
        this.dateFormatter = dateFormatter;
        this.bcrypt = bcrypt;
    }

    public Mono<BirthdayDTO> apply(@Valid BirthdayDTO dto) {
        return repository.findById(dto.getId())
                .switchIfEmpty(Mono.error(() -> new IllegalStateException("Birthday not found " + dto.getId())))
                .filter(entity -> bcrypt.compare(dto.getSecret(), entity.getSecret()))
                .switchIfEmpty(Mono.error(() -> new IllegalStateException("Birthday's secret do not match " + dto.getId())))
                .flatMap(entity -> {
                    dateFormatter.setUserAgeFromDate(dto);
                    dto.setSecret(entity.getSecret());
                    return repository.save(mapper.dtoToEntity(dto));
                })
                .map(mapper::entityToDTO);

    }


}
