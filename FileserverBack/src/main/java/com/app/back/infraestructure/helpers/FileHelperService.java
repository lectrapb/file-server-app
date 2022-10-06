package com.app.back.infraestructure.helpers;

import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

public interface FileHelperService {

    Mono<String> uploadFile(FilePart filePart, String name);
}
