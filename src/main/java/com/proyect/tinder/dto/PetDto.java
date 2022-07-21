package com.proyect.tinder.dto;

import com.proyect.tinder.enums.Sex;
import com.proyect.tinder.model.Photo;
import com.proyect.tinder.model.User;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetDto {

    private Integer idPet;
    private String name;
    private Sex sex;
    private User user;
    private Photo photo;
}
