package com.exampleapp.my_example_app.repository.interfaces;

import com.exampleapp.my_example_app.entity.PhotoEntity;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface PhotoRepositoryJPA extends Repository<PhotoEntity,Integer> {

    /**
     * Method get PhotoEntities by album number
     *
     * Response is byteArray of current picture
     * Url photos/init
     *
     * @param albumId Integer
     * @author AleksGor
     */
    List<PhotoEntity> findAllByAlbumId(int albumId);

    /**
     * Method for getting all PhotoEntities from our data base
     *
     * Response is list of All PhotoEntities
     * Url photos/all
     *
     * @author AleksGor
     */
    List<PhotoEntity> findAll();

}
