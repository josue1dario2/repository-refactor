package com.proyect.tinder.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@SQLDelete(sql = "UPDATE user SET deleted = true, deleted_at = CURRENT_TIMESTAMP WHERE id=?")
@Where(clause = "deleted = false")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private Boolean deleted = Boolean.FALSE;

    @ManyToOne
    private Zone zone;
    
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime deletedAt;
}
