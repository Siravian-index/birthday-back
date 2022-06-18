package com.sofka.birthdayprojectback.birthdaytracker.usecases;

import com.sofka.birthdayprojectback.birthdaytracker.repository.BirthdayRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeleteBirthdayUseCase {
    private final BirthdayRepository repository;

    public DeleteBirthdayUseCase(BirthdayRepository repository) {
        this.repository = repository;
    }

    public Mono<Void> apply (String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(() -> new IllegalStateException("Birthday not found  " + id)))
                .flatMap(entity -> repository.deleteById(entity.getId()));
    }
}
