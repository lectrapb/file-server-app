package com.app.back.domain.usecase.filestorage.download;

import com.app.back.domain.model.fileStorage.gateways.FileRepositoryService;
import org.junit.jupiter.api.BeforeEach;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class DownloadFileUseCaseTest {

    private FileRepositoryService fileRepository;

    private  DownloadFileUseCase useCase;

    @BeforeEach
    void setUp(){
        fileRepository = mock(FileRepositoryService.class);
        useCase = new DownloadFileUseCase(fileRepository);
    }
}