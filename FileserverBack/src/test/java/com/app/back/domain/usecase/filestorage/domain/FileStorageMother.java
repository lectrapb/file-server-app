package com.app.back.domain.usecase.filestorage.domain;

import com.app.back.domain.model.fileStorage.FileStorage;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.UUID;

public class FileStorageMother {

    public static Flux<FileStorage> fileOkFlux() {

        return Flux.just(fileOk());
    }

    public static Mono<FileStorage> fileOkMono(){

        return Mono.just(fileOk());
    }

    private static FileStorage fileOk(){
        return FileStorage.builder()
                .id(UUID.randomUUID().toString())
                .name("file-test")
                .createAt(new Date())
                .content("C://")
                .type(".pdf")
                .build();
    }
}
