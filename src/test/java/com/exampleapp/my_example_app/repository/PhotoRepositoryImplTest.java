package com.exampleapp.my_example_app.repository;

import com.exampleapp.my_example_app.dto.PhotoRequestDTO;
import com.exampleapp.my_example_app.dto.PhotoResponseDTO;
import com.exampleapp.my_example_app.entity.PhotoEntity;
import com.exampleapp.my_example_app.repository.interfaces.PhotoRepository;
import com.exampleapp.my_example_app.repository.interfaces.PhotoRepositoryJPA;
import com.exampleapp.my_example_app.service.interfaces.Mapper;
import com.exampleapp.my_example_app.service.interfaces.PhotoService;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
class PhotoRepositoryImplTest {

    @MockBean
    PhotoRepository repository;

    @MockBean
    PhotoRepositoryJPA repositoryJPA;

    @Autowired
    PhotoService service;

    @Autowired
    Mapper mapper;

    private PhotoRequestDTO photoRequestDTO;
    private PhotoRequestDTO photoRequestDTO2;
    private List<PhotoRequestDTO> checkListDto;
    private List<PhotoEntity> listFromRepository;

    @Test
    void init() {
        initialization();

        Mockito.doReturn(listFromRepository).when(repository).init(ArgumentMatchers.any());
        List<PhotoResponseDTO> responseDTOS = service.init(checkListDto);
        assertNotNull(responseDTOS);
        assertNotNull(responseDTOS.get(0));

    }

    @Test
    void initWithNull(){
        initialization();

        Mockito.doThrow(NullPointerException.class).when(repository).init(null);
        assertThrows(NullPointerException.class,()->service.init(null));

    }

    @Test
    void getAllPhotos() {
        initialization();

        Mockito.doReturn(listFromRepository).when(repositoryJPA).findAll();
        List<PhotoResponseDTO> responseDTOS = service.getAllPhotos();
        assertNotNull(responseDTOS);
        assertNotNull(responseDTOS.get(0));

    }

    @Test
    void getAllPhotosFromAlbum() {
        initialization();

        Mockito.doReturn(listFromRepository).when(repositoryJPA).findAllByAlbumId(1);
        List<PhotoResponseDTO> responseDTOS = service.getAllPhotos();
        assertNotNull(responseDTOS);

    }

    @Test
    void getAllPhotosFromAlbumWithNotExistsAlbum() {
        initialization();

        Mockito.doThrow(NullPointerException.class).when(repositoryJPA).findAllByAlbumId(5);
        assertThrows(NullPointerException.class,()->service.init(null));

    }

    @Before
    void initialization(){
        photoRequestDTO = new PhotoRequestDTO(1,
                1,
                "SomeTitle",
                "https://s3.amazonaws.com/shielddevtest/photo1.jfif",
                "https://s3.amazonaws.com/shielddevtest/photo1.jfif");
        photoRequestDTO2 = new PhotoRequestDTO(2,
                2,
                "SomeTitle",
                "https://s3.amazonaws.com/shielddevtest/photo2.jpg",
                "https://s3.amazonaws.com/shielddevtest/photo2.jpg");
        checkListDto = new ArrayList<>();
        checkListDto.add(photoRequestDTO);
        checkListDto.add(photoRequestDTO2);
        listFromRepository = new ArrayList<>();
        listFromRepository.add(mapper.map(photoRequestDTO));
        listFromRepository.add(mapper.map(photoRequestDTO2));
    }

    @After
    void afterTest(){
        photoRequestDTO = null;
        photoRequestDTO2 = null;
        checkListDto = null;
    }
}