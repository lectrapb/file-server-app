package com.app.back.domain.usecase.filestorage.search;


import com.app.back.domain.model.fileStorage.FileStorage;
import com.app.back.domain.model.fileStorage.gateways.FileRepositoryService;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

@AllArgsConstructor
public class ListFilesUseCase {

    private final FileRepositoryService fileRepository;

    public Flux<FileStorage> list(){

         return fileRepository.findAll();
    }
}
