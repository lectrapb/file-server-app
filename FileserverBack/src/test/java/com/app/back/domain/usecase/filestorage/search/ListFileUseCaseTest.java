package com.app.back.domain.usecase.filestorage.search;

import com.app.back.domain.model.fileStorage.gateways.FileRepositoryService;
import com.app.back.domain.usecase.filestorage.domain.FileStorageMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ListFileUseCaseTest {

    private FileRepositoryService fileRepository;

    private ListFilesUseCase useCase;

    @BeforeEach
    void setUp() {
        fileRepository = mock(FileRepositoryService.class);
        useCase = new ListFilesUseCase(fileRepository);
    }

    @Test
    void list_file_use_case_null_test(){
         when(fileRepository.findAll()).thenReturn(Flux.empty());
         var response = useCase.list();
         StepVerifier.create(response)
                .expectNext()
                .verifyComplete();
    }

    @Test
    void list_file_use_case_ok_test(){

         when(fileRepository.findAll()).thenReturn(FileStorageMother.fileOkFlux());
         useCase.list()
                 .as(StepVerifier:: create)
                 .expectNextCount(1)
                 .verifyComplete();

    }
}