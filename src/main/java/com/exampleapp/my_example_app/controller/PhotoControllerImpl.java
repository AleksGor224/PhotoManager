package com.exampleapp.my_example_app.controller;

import com.exampleapp.my_example_app.controller.interfaces.PhotoController;
import com.exampleapp.my_example_app.dto.PhotoRequestDTO;
import com.exampleapp.my_example_app.dto.PhotoResponseDTO;
import com.exampleapp.my_example_app.service.interfaces.PhotoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("photo")
public class PhotoControllerImpl implements PhotoController {

    @Autowired
    PhotoService photoService;

    @Autowired
    ObjectMapper objectMapper;

    @Value("${data.url}")
    String url;

    /**
     * Initialization endPoint of PhotoController. Used url of jsons from application.properties file.
     * Method send json data to service.
     * <p>
     * Response is List of all PhotoEntities
     * Url photos/init
     *
     * @author AleksGor
     */
    @Override
    @PostMapping("init")
    public List<PhotoResponseDTO> init() {

        try {
            List<PhotoRequestDTO> list = Arrays.asList(objectMapper.readValue(
                    new URL(url), PhotoRequestDTO[].class));
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

    /**
     * Method for getting all PhotoEntities from our data base.
     * <p>
     * Response is List of all PhotoEntities
     * Url photos/getAll
     *
     * @author AleksGor
     */
    @Override
    @GetMapping("all")
    public List<PhotoResponseDTO> getAllPhotos() {
        return photoService.getAllPhotos();
    }

    /**
     * Method for getting all PhotoEntities filtered by album ID from our data base.
     * <p>
     * Response is List of all PhotoEntities filtered by album ID
     * Url photos/getAll/{albumID}
     *
     * @param album Integer of album ID
     * @author AleksGor
     */
    @Override
    @GetMapping("all/{albumId}")
    public List<PhotoResponseDTO> getAllPhotosFromAlbum(@PathVariable("albumId") int album) {
        return photoService.getAllPhotosFromAlbum(album);
    }

    /**
     * Method for getting photo by local path from our backend
     * <p>
     * Response ByteArrayResource in wrapper of ResponseEntity
     * Url photos/download?path=
     *
     * @param path String of local path
     * @author AleksGor
     */
    @Override
    @GetMapping("download")
    public ResponseEntity<ByteArrayResource> getPhoto(@RequestParam("path") String path) throws FileNotFoundException {
        ByteArrayResource imageByteArr = photoService.getPhoto(path);
        return ResponseEntity
                .ok()
                .header(CacheControl.noCache().getHeaderValue())
                .contentType(MediaType.IMAGE_JPEG)
                .contentLength(imageByteArr.contentLength()).body(imageByteArr);
    }
}
