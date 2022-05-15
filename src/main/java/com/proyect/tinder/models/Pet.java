package com.proyect.tinder.models;

import com.proyect.tinder.enumerations.Sex;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE pet SET deleted = true, deleted_at = CURRENT_TIMESTAMP WHERE id=?")
@Where(clause = "deleted = false")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @ManyToOne
    private User user;

    private Boolean deleted = Boolean.FALSE;

    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;


}
