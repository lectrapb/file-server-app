package com.app.back.domain.usecase.filestorage.delete;


import com.app.back.domain.model.exception.BusinessException;
import com.app.back.domain.model.fileStorage.gateways.FileRepositoryService;
import com.app.back.domain.model.util.Constant;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class DeleteFileUseCase {


      private final FileRepositoryService fileRepository;

      public Mono<Void> delete(String name){

           return Mono.fromCallable(() -> name)
                   .switchIfEmpty(Mono.error(new BusinessException(Constant.ERROR_MISSING_PARAMS)))
                   .flatMap(nameFile -> fileRepository
                           .findByName(name)
                           .next()
                           .flatMap(fileStorage -> fileRepository.deleteById(fileStorage.getId())));
      }
}
