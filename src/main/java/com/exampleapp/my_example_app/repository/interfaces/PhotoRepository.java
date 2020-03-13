package com.exampleapp.my_example_app.repository.interfaces;

import com.exampleapp.my_example_app.entity.PhotoEntity;
import org.springframework.http.ResponseEntity;

import java.nio.file.Path;
import java.util.List;

public interface PhotoRepository {

    List<PhotoEntity> init(List<PhotoEntity> list);
    List<PhotoEntity> getAllPhotos();
    List<PhotoEntity> getAllPhotosFromAlbum(int album);
    byte[] getPhoto(Path path);
}
