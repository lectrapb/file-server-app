package com.app.back.domain.model.fileStorage;

import lombok.Data;

import java.util.Date;

@Data
public class FileStorage {

    private String id;
    private String name;
    private String type;
    private Date createAt;
    private String content;
}
