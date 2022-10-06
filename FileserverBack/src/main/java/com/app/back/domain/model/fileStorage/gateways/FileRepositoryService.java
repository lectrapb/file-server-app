package com.app.back.domain.model.fileStorage.gateways;

import com.app.back.domain.model.fileStorage.FileStorage;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FileRepositoryService {


    Mono<FileStorage> save(FileStorage fileStorage);

    Flux<FileStorage> findByName(String fileName);

    Flux<FileStorage> findAll();

    Mono<Void> deleteById(String id);
}
