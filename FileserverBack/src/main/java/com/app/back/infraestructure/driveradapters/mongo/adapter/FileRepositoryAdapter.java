package com.app.back.infraestructure.driveradapters.mongo.adapter;


import com.app.back.domain.model.fileStorage.FileStorage;
import com.app.back.domain.model.fileStorage.gateways.FileRepositoryService;
import com.app.back.infraestructure.driveradapters.mongo.mapper.MapperFileStorage;
import com.app.back.infraestructure.driveradapters.mongo.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class FileRepositoryAdapter implements FileRepositoryService {


    private final FileRepository repository;

    @Autowired
    public FileRepositoryAdapter(FileRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<FileStorage> save(FileStorage fileStorage) {
        return repository
                .insert(MapperFileStorage.toEntity(fileStorage))
                .map(MapperFileStorage::toModel);
    }

    @Override
    public Flux<FileStorage> findByName(String fileName) {
        return repository
                .searchByName(fileName)
                .map(MapperFileStorage::toModel);
    }

    @Override
    public Flux<FileStorage> findAll() {
        return repository
                .findAll()
                .map(MapperFileStorage::toModel);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }
}
