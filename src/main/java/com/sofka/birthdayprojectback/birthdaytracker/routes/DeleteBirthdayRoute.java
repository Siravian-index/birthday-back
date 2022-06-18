package com.sofka.birthdayprojectback.birthdaytracker.routes;

import com.sofka.birthdayprojectback.birthdaytracker.usecases.DeleteBirthdayUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DeleteBirthdayRoute {
    @Bean
    public RouterFunction<ServerResponse> deleteBirthday(DeleteBirthdayUseCase useCase) {
        return route(DELETE("/v1/api/birthday/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> useCase.apply(request.pathVariable("id"))
                        .flatMap(unused -> ServerResponse.status(HttpStatus.ACCEPTED).build())
                        .onErrorResume(throwable -> {
                            System.out.println(throwable.getMessage());
                            return ServerResponse.status(HttpStatus.NOT_FOUND).build();
                        })
        );
    }
}
