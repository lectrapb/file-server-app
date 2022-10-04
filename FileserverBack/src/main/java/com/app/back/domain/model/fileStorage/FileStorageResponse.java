package com.app.back.domain.model.fileStorage;


import lombok.Data;

@Data
public class FileStorageResponse {

    private String id;
    private String name;
    private String type;
    private String error;
}
