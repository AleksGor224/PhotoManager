package com.exampleapp.my_example_app.service;

import com.exampleapp.my_example_app.dto.PhotoRequestDTO;
import com.exampleapp.my_example_app.dto.PhotoResponseDTO;
import com.exampleapp.my_example_app.entity.PhotoEntity;
import com.exampleapp.my_example_app.repository.interfaces.PhotoRepository;
import com.exampleapp.my_example_app.service.interfaces.Mapper;
import com.exampleapp.my_example_app.service.interfaces.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
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
        return photoRepository.getAllPhotos().stream()
                .map((e)->mapper.map(e))
                .collect(Collectors.toList());
    }

    @Override
    public List<PhotoResponseDTO> getAllPhotosFromAlbum(int album) {
        return photoRepository.getAllPhotosFromAlbum(album).stream()
                .map((e)->mapper.map(e))
                .collect(Collectors.toList());
    }

    @Override
    public BufferedImage getPhoto(String path) {
        Path tmp = Path.of(path);
        BufferedImage img = null;
        byte[] arr = photoRepository.getPhoto(tmp);
        ByteArrayInputStream bais = new ByteArrayInputStream(arr);
        try {
            img = ImageIO.read(bais);
            System.out.println(img);
            return img;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(img);
        return img;
    }


}
