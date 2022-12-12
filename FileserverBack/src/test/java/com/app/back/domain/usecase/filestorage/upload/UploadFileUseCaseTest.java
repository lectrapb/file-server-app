package com.app.back.domain.usecase.filestorage.upload;

import com.app.back.domain.model.exception.BusinessException;
import com.app.back.domain.model.fileStorage.FileStorage;
import com.app.back.domain.model.fileStorage.gateways.FileRepositoryService;
import com.app.back.domain.model.util.Constant;
import com.app.back.domain.usecase.filestorage.domain.FileStorageMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UploadFileUseCaseTest {

    private FileRepositoryService fileRepository;
    private UploadFileUseCase useCase;

    @BeforeEach
    void setUp() {
        fileRepository = mock(FileRepositoryService.class);
        useCase = new UploadFileUseCase(fileRepository);
    }

    @Test
    void upload_use_case_null_test(){

        useCase.upload(null)
                .as(StepVerifier::create)
                .expectErrorMatches(err -> err instanceof BusinessException &&
                         err.getMessage().equals(Constant.ERROR_MISSING_PARAMS))
                .verify();
    }

    @Test
    void upload_use_case_ok_test(){

        when(fileRepository.save(any())).thenReturn(FileStorageMother.fileOkMono());
        useCase.upload(FileStorage.builder().build())
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
    }


}