package com.app.back.infraestructure.entrypoints.controller;


import com.app.back.domain.model.exception.BusinessException;
import com.app.back.domain.model.fileStorage.FileStorage;
import com.app.back.domain.model.fileStorage.FileStorageResponse;
import com.app.back.domain.model.util.Constant;
import com.app.back.domain.usecase.filestorage.upload.UploadFileUseCase;
import com.app.back.infraestructure.helpers.FileHelperService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UploadFileController {

    private final UploadFileUseCase uploadFileUseCase;
    private final FileHelperService fileHelper;

    @PostMapping(value = Constant.PATH_UPLOAD_FILES, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public Mono<ResponseEntity<FileStorageResponse>> upload(@RequestPart("file") Mono<FilePart> filePart) {

        Mono<FileStorageResponse> responseMono = filePart
                .flatMap(part -> fileHelper.uploadFile(part , part.filename()))
                .onErrorResume(e -> Mono.error(new BusinessException(e.getMessage())))
                .zipWith(buildFileStorageMono(filePart),(objectId,fileStorage ) -> FileStorage.
                         builder().name(fileStorage.getName()).type(fileStorage.getType())
                        .content(objectId).build())
                .flatMap(uploadFileUseCase::upload);

        return responseMono
                .map(p -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p))
                .onErrorResume(thr -> Mono.just(thr)
                        .flatMap(e ->{
                            FileStorageResponse dtoFile = new FileStorageResponse();
                            dtoFile.setError(e.getMessage());
                            return Mono.just(ResponseEntity.badRequest().body(dtoFile));
                        }));
    }

    private  Mono<FileStorage> buildFileStorageMono(Mono<FilePart> filePart){
        return filePart
                .map(fileP -> FileStorage.builder()
                             .name(fileP.filename())
                             .type(fileP.headers().getContentType().toString())
                             .build());
    }
}
