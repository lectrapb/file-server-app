package com.app.back.infraestructure.driveradapters.mongo;

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

        FileStorage model = new FileStorage();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setType(entity.getType());
        model.setContent(entity.getContent());
        model.setCreateAt(entity.getCreateAt());
        return model;

    }
}
