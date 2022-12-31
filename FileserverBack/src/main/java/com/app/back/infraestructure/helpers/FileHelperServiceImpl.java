package com.app.back.infraestructure.helpers;

import com.app.back.domain.model.exception.BusinessException;
import com.app.back.domain.model.util.Constant;
import com.app.back.infraestructure.driveradapters.mongo.entity.FileStorageEntity;
import com.app.back.infraestructure.driveradapters.mongo.repository.FileRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.gridfs.ReactiveGridFsTemplate;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class FileHelperServiceImpl implements FileHelperService {

    private final ReactiveGridFsTemplate gridFsTemplate;
    private final FileRepository fileRepository;

    @Override
    public Mono<String> uploadFile(FilePart filePart, String name) {

        return fileRepository
                .searchByName(name)
                .next()
                .flatMap(fileStorageEntity -> Mono.error(new BusinessException(Constant.ERROR_NAME_FILE_EXIST)))
                .onErrorResume(e -> Mono.error(new BusinessException(e.getMessage())))
                .switchIfEmpty(Mono.from(gridFsTemplate.store(filePart.content(), filePart.filename())
                        .flatMap(objectId -> Mono.fromCallable(() -> new FileStorageEntity(objectId.toString())))))
                .map(object -> {
                    FileStorageEntity fileStorage = (FileStorageEntity) object;
                    return fileStorage.getContent();
                });

    }
}
