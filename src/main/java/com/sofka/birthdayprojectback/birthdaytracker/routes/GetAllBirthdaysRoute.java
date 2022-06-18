package com.sofka.birthdayprojectback.birthdaytracker.routes;


import com.mongodb.internal.connection.Server;
import com.sofka.birthdayprojectback.birthdaytracker.dto.BirthdayDTO;
import com.sofka.birthdayprojectback.birthdaytracker.usecases.GetAllBirthdaysUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GetAllBirthdaysRoute {

    @Bean
    @RouterOperation(path = "/v1/api/birthday", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetAllBirthdaysUseCase.class, method = RequestMethod.GET, beanMethod = "apply",
            operation = @Operation(operationId = "getBirthdays", tags = "Birthday",responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation",
                            content = @Content(schema = @Schema(implementation = BirthdayDTO.class)))})
    )
    public RouterFunction<ServerResponse> getAllBirthdays(GetAllBirthdaysUseCase useCase) {
        return route(GET("/v1/api/birthday"),
                request -> ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromProducer(useCase.apply(), BirthdayDTO.class)));
    }
}
