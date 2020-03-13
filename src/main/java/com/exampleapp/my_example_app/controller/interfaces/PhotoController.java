package com.exampleapp.my_example_app.controller.interfaces;

import com.exampleapp.my_example_app.dto.PhotoResponseDTO;
import org.springframework.http.ResponseEntity;

import java.awt.image.BufferedImage;
import java.util.List;

public interface PhotoController {

    List<PhotoResponseDTO> init();
    List<PhotoResponseDTO> getAllPhotos();
    List<PhotoResponseDTO> getAllPhotosFromAlbum(int album);
    ResponseEntity<BufferedImage> getPhoto(String path);
}
