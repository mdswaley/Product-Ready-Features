package com.example.productionreadyfeatures.Entity;

import jakarta.persistence.*;
import lombok.*;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "postData")
@Audited //this annotation means inside this class all the field are audited now(we can see who ever change and which time change)
public class PostEntity extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    //@NotAudited // this is not auditing means what ever make change in descr it is not reflect or trigger in database (who and when)
    private String descr;

//    we can paste our auditing data in parent class so that other entity can easily extend it and use it.

    @PrePersist
    void beforeSave(){
    }

    @PreUpdate
    void beforeUpdate(){

    }
    @PreRemove
    void beforeRemove(){
    }

}
