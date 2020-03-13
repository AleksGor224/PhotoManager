package com.exampleapp.my_example_app.service.interfaces;

import com.exampleapp.my_example_app.dto.PhotoRequestDTO;
import com.exampleapp.my_example_app.dto.PhotoResponseDTO;
import org.springframework.core.io.ByteArrayResource;

import java.io.FileNotFoundException;
import java.util.List;

public interface PhotoService {


    /**
     * Method Initializations of news dtoRequests objects in our data base,
     * does mapping operations and set upload date
     *
     * Response is List of all PhotoResponseDto
     *
     * @author AleksGor
     * @param list of new PhotoRequestDTOs
     */
    List<PhotoResponseDTO> init(List<PhotoRequestDTO> list);

    /**
     * Method for getting all PhotoEntities from our data base.
     *
     * Response is List of all PhotoEntities
     *
     * @author AleksGor
     */
    List<PhotoResponseDTO> getAllPhotos();

    /**
     * Method for getting all PhotoEntities filtered by album ID from our data base.
     *
     * Response is List of all PhotoEntities filtered by album ID
     *
     * @author AleksGor
     * @param album Integer of album ID
     */
    List<PhotoResponseDTO> getAllPhotosFromAlbum(int album);

    /**
     * Method for getting photo by local path from our backend
     *
     * Response ByteArrayResource of picture
     *
     * @author AleksGor
     * @param path String of local path
     */
    ByteArrayResource getPhoto(String path) throws FileNotFoundException;
}
