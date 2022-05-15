package com.proyect.tinder.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@SQLDelete(sql = "UPDATE vote SET deleted = true, deleted_at = CURRENT_TIMESTAMP WHERE id=?")
@Where(clause = "deleted = false")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;
    private LocalDateTime response;

    private Boolean deleted = Boolean.FALSE;

    @ManyToOne
    private Pet pet1;

    @ManyToOne
    private Pet pet2;

    @LastModifiedDate
    private LocalDateTime deletedAt;
}
