package com.app.back.config;


import com.app.back.domain.usecase.filestorage.delete.DeleteFileUseCase;
import com.app.back.domain.usecase.filestorage.download.DownloadFileUseCase;
import com.app.back.domain.usecase.filestorage.playVideo.PlayVideoUseCase;
import com.app.back.domain.usecase.filestorage.search.ListFilesUseCase;
import com.app.back.domain.usecase.filestorage.upload.UploadFileUseCase;
import com.app.back.infraestructure.driveradapters.mongo.adapter.FileRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigUseCase {

    @Bean
    public ListFilesUseCase listFilesUseCase(FileRepositoryAdapter fileRepositoryAdapter){
        return  new ListFilesUseCase(fileRepositoryAdapter);
    }

    @Bean
    public DownloadFileUseCase downloadFileUseCase(FileRepositoryAdapter fileRepositoryAdapter){
        return new DownloadFileUseCase(fileRepositoryAdapter);
    }

    @Bean
    public PlayVideoUseCase playVideoUseCase (FileRepositoryAdapter fileRepositoryAdapter){
        return new PlayVideoUseCase(fileRepositoryAdapter);
    }

    @Bean
    public UploadFileUseCase uploadFileUseCase(FileRepositoryAdapter fileRepositoryAdapter){
        return  new UploadFileUseCase(fileRepositoryAdapter);
    }


    @Bean
    public DeleteFileUseCase deleteFileUseCase(FileRepositoryAdapter fileRepositoryAdapter){
        return  new DeleteFileUseCase(fileRepositoryAdapter);
    }



}
