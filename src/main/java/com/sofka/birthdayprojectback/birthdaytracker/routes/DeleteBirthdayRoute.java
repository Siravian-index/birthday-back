package com.sofka.birthdayprojectback.birthdaytracker.routes;

import com.sofka.birthdayprojectback.birthdaytracker.dto.BirthdayDTO;
import com.sofka.birthdayprojectback.birthdaytracker.usecases.DeleteBirthdayUseCase;
import com.sofka.birthdayprojectback.birthdaytracker.usecases.PutBirthdayUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DeleteBirthdayRoute {
    @Bean
    @RouterOperation(path = "/v1/api/birthday", produces = {
            MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.DELETE, beanClass = DeleteBirthdayUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "deleteBirthday", tags = "Birthday", responses = {
                    @ApiResponse(responseCode = "202", description = "successful operation", content = @Content(schema = @Schema(implementation = BirthdayDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid Birthday details supplied")}
                    , requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = BirthdayDTO.class)))
            ))
    public RouterFunction<ServerResponse> deleteBirthday(DeleteBirthdayUseCase useCase) {
        return route(DELETE("/v1/api/birthday/").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(BirthdayDTO.class)
                        .flatMap(useCase::apply)
                        .flatMap(unused -> ServerResponse.status(HttpStatus.ACCEPTED).build())
                        .onErrorResume(throwable -> {
                            System.out.println(throwable.getMessage());
                            return ServerResponse.status(HttpStatus.BAD_REQUEST).build();
                        })
        );
    }
}
