package com.exampleapp.my_example_app.service.interfaces;

import com.exampleapp.my_example_app.dto.PhotoRequestDTO;
import com.exampleapp.my_example_app.dto.PhotoResponseDTO;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;

import java.awt.image.BufferedImage;
import java.util.List;

public interface PhotoService {

    List<PhotoResponseDTO> init(List<PhotoRequestDTO> list);
    List<PhotoResponseDTO> getAllPhotos();
    List<PhotoResponseDTO> getAllPhotosFromAlbum(int album);
    ByteArrayResource getPhoto(String path);
}
