package com.app.back.infraestructure.driveradapters.mongo.mapper;

import com.app.back.domain.model.fileStorage.FileStorage;
import com.app.back.infraestructure.driveradapters.mongo.entity.FileStorageEntity;

public class MapperFileStorage {
    public static FileStorageEntity toEntity(FileStorage model) {

        FileStorageEntity entity = new FileStorageEntity();
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setType(model.getType());
        entity.setContent(model.getContent());
        entity.setCreateAt(model.getCreateAt());
        return entity;
    }

    public static FileStorage toModel(FileStorageEntity entity) {

        return FileStorage.builder()
                .id(entity.getId())
                .name(entity.getName())
                .type(entity.getType())
                .content(entity.getContent())
                .createAt(entity.getCreateAt())
                .build();


    }
}
