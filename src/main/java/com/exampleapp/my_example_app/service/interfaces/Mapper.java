package com.exampleapp.my_example_app.service.interfaces;

import com.exampleapp.my_example_app.dtos.PhotoRequestDTO;
import com.exampleapp.my_example_app.dtos.PhotoResponseDTO;
import com.exampleapp.my_example_app.entities.PhotoEntity;

public interface Mapper {

    PhotoEntity map(PhotoRequestDTO from);
    PhotoResponseDTO map(PhotoEntity from);
}
