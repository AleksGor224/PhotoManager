package com.exampleapp.my_example_app.service.interfaces;

import com.exampleapp.my_example_app.dtos.PhotoRequestDTO;
import com.exampleapp.my_example_app.dtos.PhotoResponseDTO;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PhotoService {

    List<PhotoResponseDTO> init(List<PhotoRequestDTO> list);
    List<PhotoResponseDTO> getAllPhotos();
    List<PhotoResponseDTO> getAllPhotosFromAlbum(int album);
    ResponseEntity<PhotoResponseDTO> getPhoto(String path);
}
