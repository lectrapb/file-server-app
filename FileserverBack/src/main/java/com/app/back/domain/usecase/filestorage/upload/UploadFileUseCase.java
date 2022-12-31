package com.app.back.domain.usecase.filestorage.upload;

import com.app.back.domain.model.exception.BusinessException;
import com.app.back.domain.model.fileStorage.FileStorage;
import com.app.back.domain.model.fileStorage.FileStorageResponse;
import com.app.back.domain.model.fileStorage.gateways.FileRepositoryService;
import com.app.back.domain.model.util.Constant;
import com.app.back.domain.usecase.filestorage.upload.MapperToResponse;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Date;

@AllArgsConstructor
public class UploadFileUseCase {

     private final FileRepositoryService fileRepository;

     public Mono<FileStorageResponse> upload(FileStorage file){

        return    Mono.fromCallable(() -> file)
                  .switchIfEmpty(Mono.error(new BusinessException(Constant.ERROR_MISSING_PARAMS)))
                  .flatMap(fileOrigin ->{
                       fileOrigin.setCreateAt(new Date());
                       return fileRepository
                               .save(fileOrigin)
                               .flatMap(fileMono -> Mono.fromCallable(() -> MapperToResponse.toResponse(fileMono)));
                  });
     }
}
