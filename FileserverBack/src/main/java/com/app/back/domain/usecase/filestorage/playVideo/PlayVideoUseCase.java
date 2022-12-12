package com.app.back.domain.usecase.filestorage.playVideo;

import com.app.back.domain.model.exception.BusinessException;
import com.app.back.domain.model.fileStorage.FileStorage;
import com.app.back.domain.model.fileStorage.gateways.FileRepositoryService;
import com.app.back.domain.model.util.Constant;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class PlayVideoUseCase {

    private final FileRepositoryService fileRepository;

    public Mono<FileStorage> play(String name){

          return Mono.fromCallable(() -> name)
                          .switchIfEmpty(Mono.error(new BusinessException(Constant.ERROR_MISSING_PARAMS)))
                          .flatMap(nameVideo -> fileRepository.findByName(name)
                                  .switchIfEmpty(Mono.error(new BusinessException(Constant.ERROR_MISSING_VIDEO)))
                                  .next());
    }
}
