package com.app.back.domain.usecase.filestorage.upload;

import com.app.back.domain.model.exception.BusinessException;
import com.app.back.domain.model.fileStorage.FileStorage;
import com.app.back.domain.model.fileStorage.gateways.FileRepositoryService;
import com.app.back.domain.model.util.Constant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Date;
import java.util.UUID;

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

        when(fileRepository.save(any())).thenReturn(createFileOk());
        useCase.upload(new FileStorage())
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
    }

    private static Mono<FileStorage> createFileOk(){
        FileStorage file = new FileStorage();
        file.setId(UUID.randomUUID().toString());
        file.setName("fila-test");
        file.setCreateAt(new Date());
        file.setContent("C://");
        file.setType(".pdf");
        return Mono.just(file);
    }
}