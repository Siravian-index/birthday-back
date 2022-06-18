package com.sofka.birthdayprojectback.birthdaytracker.usecases;

import com.sofka.birthdayprojectback.birthdaytracker.bcrypt.BcryptMapper;
import com.sofka.birthdayprojectback.birthdaytracker.dto.BirthdayDTO;
import com.sofka.birthdayprojectback.birthdaytracker.repository.BirthdayRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Service
@Validated
public class DeleteBirthdayUseCase {
    private final BirthdayRepository repository;
    private final BcryptMapper bcrypt;

    public DeleteBirthdayUseCase(BirthdayRepository repository, BcryptMapper bcrypt) {
        this.repository = repository;
        this.bcrypt = bcrypt;
    }

    public Mono<Void> apply (@Valid BirthdayDTO dto) {
        return repository.findById(dto.getId())
                .switchIfEmpty(Mono.error(() -> new IllegalStateException("Birthday not found  " + dto.getId())))
                .filter(entity -> bcrypt.compare(dto.getSecret(), entity.getSecret()))
                .switchIfEmpty(Mono.error(() -> new IllegalStateException("Secrets do not match")))
                .flatMap(entity -> repository.deleteById(entity.getId()));
    }
}
