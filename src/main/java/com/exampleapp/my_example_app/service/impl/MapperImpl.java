package com.exampleapp.my_example_app.service.impl;

import com.exampleapp.my_example_app.dtos.PhotoRequestDTO;
import com.exampleapp.my_example_app.dtos.PhotoResponseDTO;
import com.exampleapp.my_example_app.entities.PhotoEntity;
import com.exampleapp.my_example_app.service.interfaces.Mapper;
import org.springframework.stereotype.Component;

@Component
public class MapperImpl implements Mapper {
    @Override
    public PhotoEntity map(PhotoRequestDTO from) {
        return PhotoEntity.builder()
                .id(from.getId())
                .albumId(from.getAlbumId())
                .url(from.getUrl())
                .title(from.getTitle())
                .thumbnailUrl(from.getThumbnailUrl())
                .build();
    }

    @Override
    public PhotoResponseDTO map(PhotoEntity from) {
        return PhotoResponseDTO.builder()
                .id(from.getId())
                .albumId(from.getAlbumId())
                .title(from.getTitle())
                .url(from.getUrl())
                .thumbnailUrl(from.getThumbnailUrl())
                .downloadDateTime(from.getDownloadDateTime())
                .localPath(from.getLocalPath())
                .fileSize(from.getFileSize())
                .build();
    }
}
