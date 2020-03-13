package com.exampleapp.my_example_app.controller.impl;

import com.exampleapp.my_example_app.controller.interfaces.PhotoController;
import com.exampleapp.my_example_app.dtos.PhotoRequestDTO;
import com.exampleapp.my_example_app.dtos.PhotoResponseDTO;
import com.exampleapp.my_example_app.service.interfaces.PhotoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@RestController
public class PhotoControllerImpl implements PhotoController {

    @Autowired
    PhotoService photoService;

    @Override
    @PostMapping("init")
    public List<PhotoResponseDTO> init() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<PhotoRequestDTO> list = Arrays.asList(mapper.readValue(
                    new URL("https://s3.amazonaws.com/shielddevtest/photo.txt"), PhotoRequestDTO[].class));
            return photoService.init(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<PhotoResponseDTO> getAllPhotos() {
        return null;
    }

    @Override
    public List<PhotoResponseDTO> getAllPhotosFromAlbum(int album) {
        return null;
    }

    @Override
    public ResponseEntity<PhotoResponseDTO> getPhoto(String path) {
        return null;
    }
}
