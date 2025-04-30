package com.example.productionreadyfeatures.DTO;

import com.example.productionreadyfeatures.Entity.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto implements Serializable {
    private Long id;
    private String title;
    private String descr;
}
