package com.app.back.domain.usecase.filestorage.upload;

import com.app.back.domain.model.fileStorage.FileStorage;
import com.app.back.domain.model.fileStorage.FileStorageResponse;

public class MapperToResponse {

    public static FileStorageResponse toResponse(FileStorage file){

        FileStorageResponse response = new FileStorageResponse();
        response.setId(file.getId());
        response.setName(file.getName());
        response.setType(file.getType());
        return response;
    }
}
