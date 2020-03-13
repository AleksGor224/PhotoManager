package com.exampleapp.my_example_app.repository.interfaces;

import com.exampleapp.my_example_app.entity.PhotoEntity;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.List;

public interface PhotoRepository {

    /**
     * Method added new PhotoEntities to our data base and save new pictures from object link to pic in local folder /src/pictures
     * in folder with name of album number.
     *
     * Response is List of All PhotoEntities
     * Url photos/init
     *
     * @param list List of PhoneEntities
     * @author AleksGor
     */
    List<PhotoEntity> init(List<PhotoEntity> list);

    /**
     * Method for getting all PhotoEntities from our data base
     *
     * Response is lisr of All PhotoEntities
     * Url photos/all
     *
     * @author AleksGor
     */
    List<PhotoEntity> getAllPhotos();

    /**
     * Method get PhotoEntities by album number
     *
     * Response is List of all PhotoEntities filtered by album ID
     * Url photos/init
     *
     * @param album Integer
     * @author AleksGor
     */
    List<PhotoEntity> getAllPhotosFromAlbum(int album);

    /**
     * Method get PhotoEntities by album number
     *
     * Response is byteArray of current picture
     * Url photos/init
     *
     * @param path local Path of pictures. We can look him from request getAllPhotos
     * @author AleksGor
     */
    byte[] getPhoto(Path path) throws FileNotFoundException;
}
