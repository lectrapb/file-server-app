package com.app.back.infraestructure.entrypoints.controller;

import com.app.back.domain.model.fileStorage.FileStorage;
import com.app.back.domain.model.util.Constant;
import com.app.back.domain.usecase.filestorage.search.ListFilesUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ListFilesController {

    private final ListFilesUseCase useCase;

    @GetMapping(Constant.PATH_LIST_FILES)
    public Mono<ResponseEntity<Flux<FileStorage>>> listFiles(){

        return Mono.just(ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(useCase.list()))
                    .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }
}
