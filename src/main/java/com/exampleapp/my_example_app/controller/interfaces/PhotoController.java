package com.exampleapp.my_example_app.controller.interfaces;

import com.exampleapp.my_example_app.dto.PhotoResponseDTO;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.util.List;

public interface PhotoController {

    /**
     * Initialization endPoint of PhotoController. Used url of jsons from application.properties file.
     * Method send json data to service.
     *
     * Response is List of all PhotoEntities
     * Url photos/init
     *
     * @author AleksGor
     */
    List<PhotoResponseDTO> init();

    /**
     * Method for getting all PhotoEntities from our data base.
     *
     * Response is List of all PhotoEntities
     * Url photos/getAll
     *
     * @author AleksGor
     */
    List<PhotoResponseDTO> getAllPhotos();

    /**
     * Method for getting all PhotoEntities filtered by album ID from our data base.
     *
     * Response is List of all PhotoEntities filtered by album ID
     * Url photos/getAll/{albumID}
     *
     * @author AleksGor
     * @param album Integer of album ID
     */
    List<PhotoResponseDTO> getAllPhotosFromAlbum(int album);

    /**
     * Method for getting photo by local path from our backend
     *
     * Response ByteArrayResource in wrapper of ResponseEntity
     * Url photos/download?path=
     *
     * @author AleksGor
     * @param path String of local path
     */
    ResponseEntity<ByteArrayResource> getPhoto(String path) throws FileNotFoundException;
}
