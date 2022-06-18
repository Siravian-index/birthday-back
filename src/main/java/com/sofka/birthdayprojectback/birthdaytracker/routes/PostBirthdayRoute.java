package com.sofka.birthdayprojectback.birthdaytracker.routes;

import com.sofka.birthdayprojectback.birthdaytracker.dto.BirthdayDTO;
import com.sofka.birthdayprojectback.birthdaytracker.usecases.PostBirthdayUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class PostBirthdayRoute {
    @Bean
    public RouterFunction<ServerResponse> postBirthday(PostBirthdayUseCase useCase) {
        return route(POST("/v1/api/birthday").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(BirthdayDTO.class)
                        .flatMap(useCase::apply)
                        .flatMap(dto -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(dto))
                        .onErrorResume((error) -> ServerResponse.status(HttpStatus.BAD_REQUEST).build())
        );
    }
}
