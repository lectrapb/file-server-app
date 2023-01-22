package com.app.back.infraestructure.entrypoints.controller;

import com.app.back.domain.model.util.Constant;
import com.app.back.domain.usecase.filestorage.delete.DeleteFileUseCase;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.ReactiveGridFsTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class DeleteFileController {

    private final DeleteFileUseCase useCase;

    private final ReactiveGridFsTemplate gridFsTemplate;

    @DeleteMapping(Constant.PATH_DELETE_FILE)
    public Mono<ResponseEntity<Void>> delete(@RequestParam("name-file") String nameFile){

        Query query = new Query(GridFsCriteria.whereFilename().is(nameFile));
        return  gridFsTemplate.delete(query)
                .then(useCase.delete(nameFile))
                .then( Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT))  );

    }
}
