package com.exampleapp.my_example_app.controller;

import com.exampleapp.my_example_app.controller.interfaces.PhotoController;
import com.exampleapp.my_example_app.dto.PhotoRequestDTO;
import com.exampleapp.my_example_app.dto.PhotoResponseDTO;
import com.exampleapp.my_example_app.service.interfaces.PhotoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
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
    @GetMapping("all")
    public List<PhotoResponseDTO> getAllPhotos() {
        return photoService.getAllPhotos();
    }

    @Override
    @GetMapping("all/{albumId}")
    public List<PhotoResponseDTO> getAllPhotosFromAlbum(@PathVariable("albumId") int album) {
        return photoService.getAllPhotosFromAlbum(album);
    }

    @Override
    @GetMapping("download")
    public ResponseEntity<ByteArrayResource> getPhoto(@RequestParam("path") String path){
        ByteArrayResource bar = photoService.getPhoto(path);
        HttpHeaders headers = new HttpHeaders();
        System.out.println(bar.contentLength());
        return ResponseEntity.ok().header(CacheControl.noCache().getHeaderValue()).contentType(MediaType.IMAGE_JPEG).contentLength(bar.contentLength()).body(bar);
    }
}
