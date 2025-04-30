package com.example.productionreadyfeatures.Repository;

import com.example.productionreadyfeatures.Entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<PostEntity,Long> {
    List<PostEntity> findByTitle(String title);
}
