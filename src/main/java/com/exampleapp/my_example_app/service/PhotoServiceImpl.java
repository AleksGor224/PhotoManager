package com.exampleapp.my_example_app.service;

import com.exampleapp.my_example_app.dto.PhotoRequestDTO;
import com.exampleapp.my_example_app.dto.PhotoResponseDTO;
import com.exampleapp.my_example_app.entity.PhotoEntity;
import com.exampleapp.my_example_app.repository.interfaces.PhotoRepository;
import com.exampleapp.my_example_app.service.interfaces.Mapper;
import com.exampleapp.my_example_app.service.interfaces.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    Mapper mapper;

    /**
     * Method Initializations of news dtoRequests objects in our data base,
     * does mapping operations and set upload date
     * <p>
     * Response is List of all PhotoResponseDto
     *
     * @param list of new PhotoRequestDTOs
     * @author AleksGor
     */
    @Override
    public List<PhotoResponseDTO> init(List<PhotoRequestDTO> list) {
        List<PhotoEntity> res = list.stream()
                .map((dto) -> {
                    PhotoEntity newEntity = mapper.map(dto);
                    newEntity.setDownloadDateTime(LocalDateTime.now().toString());
                    return newEntity;
                })
                .collect(Collectors.toList());
        return photoRepository.init(res).stream()
                .map((entity) -> mapper.map(entity))
                .collect(Collectors.toList());
    }


    /**
     * Method for getting all PhotoEntities from our data base.
     * <p>
     * Response is List of all PhotoEntities
     *
     * @author AleksGor
     */
    @Override
    public List<PhotoResponseDTO> getAllPhotos() {
        return photoRepository.findAll().stream()
                .map((entity) -> mapper.map(entity))
                .collect(Collectors.toList());
    }

    /**
     * Method for getting all PhotoEntities filtered by album ID from our data base.
     * <p>
     * Response is List of all PhotoEntities filtered by album ID
     *
     * @param album Integer of album ID
     * @author AleksGor
     */
    @Override
    public List<PhotoResponseDTO> getAllPhotosFromAlbum(int album) {
        return photoRepository.findAllByAlbumId(album).stream()
                .map((entity) -> mapper.map(entity))
                .collect(Collectors.toList());
    }

    /**
     * Method for getting photo by local path from our backend
     * <p>
     * Response ByteArrayResource of picture
     *
     * @param path String of local path
     * @author AleksGor
     */
    @Override
    public ByteArrayResource getPhoto(String path) throws FileNotFoundException {
        Path tmp = Path.of(path);
        return new ByteArrayResource(photoRepository.getPhoto(tmp));
    }


}
