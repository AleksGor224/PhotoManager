package com.exampleapp.my_example_app.repository.interfaces;

import com.exampleapp.my_example_app.dtos.PhotoRequestDTO;
import com.exampleapp.my_example_app.dtos.PhotoResponseDTO;
import com.exampleapp.my_example_app.entities.PhotoEntity;
import org.springframework.http.ResponseEntity;

import java.nio.file.Path;
import java.util.List;

public interface PhotoRepository {

    List<PhotoEntity> init(List<PhotoEntity> list);
    List<PhotoEntity> getAllPhotos();
    List<PhotoEntity> getAllPhotosFromAlbum(int album);
    ResponseEntity<PhotoEntity> getPhoto(Path path);
}
