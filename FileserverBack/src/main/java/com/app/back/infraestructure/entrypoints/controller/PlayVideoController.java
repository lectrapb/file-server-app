package com.app.back.infraestructure.entrypoints.controller;


import com.app.back.domain.model.fileStorage.FileStorage;
import com.app.back.domain.model.util.Constant;
import com.app.back.domain.usecase.filestorage.playVideo.PlayVideoUseCase;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.ReactiveGridFsTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class PlayVideoController {

    private final PlayVideoUseCase useCase;

    private final ReactiveGridFsTemplate gridFsTemplate;

    @RequestMapping(value = Constant.PATH_PLAY_VIDEO, method = RequestMethod.GET)
    public Flux<Object> getVideo(@PathVariable String nameVideo, ServerWebExchange exchange){

        Flux<Object> result =  useCase.play(nameVideo)
                .map(FileStorage::getContent)
                .flatMap(content -> gridFsTemplate.findOne(Query.query(where("_id").is(content))))
                .flatMap(gridFsTemplate::getResource)
                .flatMapMany( r ->{
                    ServerHttpResponse originalResponse = exchange.getResponse();
                    originalResponse.getHeaders().set(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename="+r.getFilename()+"");
                    originalResponse.getHeaders().setContentType(MediaType.APPLICATION_OCTET_STREAM);
                    return  exchange.getResponse().writeWith(r.getDownloadStream());
                });

        return result;
    }


}
