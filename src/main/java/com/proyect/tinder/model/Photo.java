package com.proyect.tinder.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPhoto;

    private String name;
    private String mime;

    @Lob @Basic(fetch = FetchType.LAZY)
    private byte[] content;


}
