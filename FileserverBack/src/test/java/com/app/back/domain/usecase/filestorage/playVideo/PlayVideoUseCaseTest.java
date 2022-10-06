package com.app.back.domain.usecase.filestorage.playVideo;

import com.app.back.domain.model.exception.BusinessException;
import com.app.back.domain.model.fileStorage.FileStorage;
import com.app.back.domain.model.fileStorage.gateways.FileRepositoryService;
import com.app.back.domain.model.util.Constant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Date;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PlayVideoUseCaseTest {

    private FileRepositoryService fileRepository;

    private PlayVideoUseCase useCase;

    @BeforeEach
    void setUp() {
        fileRepository = mock(FileRepositoryService.class);
        useCase = new PlayVideoUseCase(fileRepository);
    }

    @Test
    void play_video_use_case_null_test(){

        useCase.play(null)
                .as(StepVerifier::create)
                .expectErrorMatches(err -> err instanceof BusinessException &&
                   err.getMessage().equals(Constant.ERROR_MISSING_PARAMS))
                .verify();
    }

    @Test
    void play_video_use_case_ok_test(){

        when(fileRepository.findByName(any())).thenReturn(createFileOk());
        useCase.play("")
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
    }

    private static Flux<FileStorage> createFileOk(){
        FileStorage file = new FileStorage();
        file.setId(UUID.randomUUID().toString());
        file.setName("fila-test");
        file.setCreateAt(new Date());
        file.setContent("C://");
        file.setType(".pdf");
        return Flux.just(file);
    }
}