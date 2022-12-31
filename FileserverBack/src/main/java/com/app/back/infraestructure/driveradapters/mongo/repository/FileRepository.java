package com.app.back.infraestructure.driveradapters.mongo.repository;


import com.app.back.infraestructure.driveradapters.mongo.entity.FileStorageEntity;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface FileRepository extends ReactiveMongoRepository<FileStorageEntity,String> {

    @Query("{ 'name':  ?0 }")
    Flux<FileStorageEntity> searchByName(String name);
}
