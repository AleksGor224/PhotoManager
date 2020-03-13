package com.exampleapp.my_example_app.service.interfaces;

import com.exampleapp.my_example_app.dto.PhotoRequestDTO;
import com.exampleapp.my_example_app.dto.PhotoResponseDTO;
import com.exampleapp.my_example_app.entity.PhotoEntity;

public interface Mapper {

    PhotoEntity map(PhotoRequestDTO from);
    PhotoResponseDTO map(PhotoEntity from);
}
