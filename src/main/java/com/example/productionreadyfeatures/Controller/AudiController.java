package com.example.productionreadyfeatures.Controller;

import com.example.productionreadyfeatures.Entity.PostEntity;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/audi")
public class AudiController {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @GetMapping(path = "/posts/{postId}")
    public List<PostEntity> getPostRevisions(@PathVariable Long postId) {
        AuditReader reader = AuditReaderFactory.get(entityManagerFactory.createEntityManager());

        List<Number> revisions = reader.getRevisions(PostEntity.class, postId);
        return revisions.stream()
                .map(rev -> reader.find(PostEntity.class, postId, rev))
                .collect(Collectors.toList());
    }



}
