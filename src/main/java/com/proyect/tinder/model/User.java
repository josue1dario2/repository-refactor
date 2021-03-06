package com.proyect.tinder.model;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @OneToOne
    private Photo photo;

    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
}
