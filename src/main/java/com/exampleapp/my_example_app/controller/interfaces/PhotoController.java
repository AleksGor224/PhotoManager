package com.exampleapp.my_example_app.controller.interfaces;

import com.exampleapp.my_example_app.dtos.PhotoResponseDTO;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PhotoController {

    List<PhotoResponseDTO> init();
    List<PhotoResponseDTO> getAllPhotos();
    List<PhotoResponseDTO> getAllPhotosFromAlbum(int album);
    ResponseEntity<PhotoResponseDTO> getPhoto(String path);
}
