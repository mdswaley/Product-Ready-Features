package com.example.productionreadyfeatures.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Audited
public class AuditingEntity {

    @CreatedDate  //store the created date.
    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate  //store the updated date and time.
    private LocalDateTime updatedAt;

    @CreatedBy //store who is created (username of that person)
    private String createdBy;

    @LastModifiedBy //store who is update (username of that person)
    private String updatedBy;
}
