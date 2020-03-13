package com.exampleapp.my_example_app.service.impl;

import com.exampleapp.my_example_app.dtos.PhotoRequestDTO;
import com.exampleapp.my_example_app.dtos.PhotoResponseDTO;
import com.exampleapp.my_example_app.entities.PhotoEntity;
import com.exampleapp.my_example_app.repository.interfaces.PhotoRepository;
import com.exampleapp.my_example_app.service.interfaces.Mapper;
import com.exampleapp.my_example_app.service.interfaces.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    Mapper mapper;

    //TODO new fields data
    @Override
    public List<PhotoResponseDTO> init(List<PhotoRequestDTO> list) {
        List<PhotoEntity> res = list.stream()
                .map((e)-> {
                    PhotoEntity tmp = mapper.map(e);
                    tmp.setDownloadDateTime(LocalDateTime.now().toString());
                    return tmp;
                })
                .collect(Collectors.toList());
        return photoRepository.init(res).stream()
                .map((e)->mapper.map(e))
                .collect(Collectors.toList());
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
