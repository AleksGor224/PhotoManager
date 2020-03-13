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
     * <p>
     * Response is List of all PhotoEntities
     * Url photos/init
     *
     * @author AleksGor
     */
    List<PhotoResponseDTO> init();

    /**
     * Method for getting all PhotoEntities from our data base.
     * <p>
     * Response is List of all PhotoEntities
     * Url photos/getAll
     *
     * @author AleksGor
     */
    List<PhotoResponseDTO> getAllPhotos();

    /**
     * Method for getting all PhotoEntities filtered by album ID from our data base.
     * <p>
     * Response is List of all PhotoEntities filtered by album ID
     * Url photos/getAll/{albumID}
     *
     * @param album Integer of album ID
     * @author AleksGor
     */
    List<PhotoResponseDTO> getAllPhotosFromAlbum(int album);

    /**
     * Method for getting photo by local path from our backend
     * <p>
     * Response ByteArrayResource in wrapper of ResponseEntity
     * Url photos/download?path=
     *
     * @param path String of local path
     * @author AleksGor
     */
    ResponseEntity<ByteArrayResource> getPhoto(String path) throws FileNotFoundException;
}
