package com.app.back.infraestructure.entrypoints.controller;


import com.app.back.domain.model.util.Constant;
import com.app.back.domain.usecase.filestorage.download.DownloadFileUseCase;
import lombok.AllArgsConstructor;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.mongodb.gridfs.ReactiveGridFsTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicReference;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class DownloadFileController {

    private final DownloadFileUseCase useCase;

    private final ReactiveGridFsTemplate gridFsTemplate;

    @GetMapping(Constant.PATH_PREVIEW)
    public Mono<ResponseEntity<Flux<DataBuffer>>> preview(@RequestParam("filename") String filename, ServerWebExchange exchange) {

        AtomicReference<MediaType> type = new AtomicReference<>(MediaType.IMAGE_PNG);
        return  useCase.download(filename)
                .map(fileStorage -> {
                    type.set(MediaType.valueOf(fileStorage.getType()));
                    return fileStorage.getContent();
                })
                .flatMap(id -> gridFsTemplate.findOne(query(where("_id").is(id))))
                .flatMap(gridFSFile -> gridFsTemplate.getResource(gridFSFile))
                .flatMap(r -> Mono.just(ResponseEntity
                                        .status(HttpStatus.OK)
                                        .contentType(type.get())
                                        .body(r.getDownloadStream())));


    }

    @GetMapping(Constant.PATH_DOWNLOAD)
    public Flux<Void> downloadFile(@RequestParam("filename") String filename, ServerWebExchange exchange) {


          return useCase.download(filename)
                .map(fileStorage -> fileStorage.getContent())
                .flatMap(id -> gridFsTemplate.findOne(query(where("_id").is(id))))
                .flatMap(gridFSFile -> gridFsTemplate.getResource(gridFSFile))
                .flatMapMany(r ->{
                            ServerHttpResponse originalResponse = exchange.getResponse();
                            originalResponse.getHeaders().set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+r.getFilename()+"");
                            originalResponse.getHeaders().setContentType(MediaType.APPLICATION_OCTET_STREAM);
                            return  exchange.getResponse().writeWith(r.getDownloadStream());
                        }
                );

    }
}
