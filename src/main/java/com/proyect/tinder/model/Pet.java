package com.proyect.tinder.model;

import com.proyect.tinder.enums.Sex;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE pet SET deleted = true, deleted_at = CURRENT_TIMESTAMP WHERE id=?")
@Where(clause = "deleted = false")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPet;

    private String name;
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @ManyToOne
    private User user;

    @OneToOne
    private Photo photo;

    private Boolean deleted = Boolean.FALSE;

    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;


}
