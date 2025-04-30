package com.example.productionreadyfeatures.Service;

import com.example.productionreadyfeatures.DTO.PostDto;
import com.example.productionreadyfeatures.Entity.PostEntity;
import com.example.productionreadyfeatures.Repository.PostRepo;
import com.example.productionreadyfeatures.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final PostRepo postRepo;
    private final ModelMapper modelMapper;
    private final String CACHE_NAME = "posts";

    @CachePut(cacheNames = CACHE_NAME, key = "#id")
    @Transactional
    public PostDto addData(PostDto postDto){
        List<PostEntity> isPostExist = postRepo.findByTitle(postDto.getTitle());

        if(!isPostExist.isEmpty()){
            log.error("Post with is present with title {}",postDto.getTitle());
            throw new RuntimeException("Post with title "+postDto.getTitle()+" is present");
        }

        PostEntity postEntity = modelMapper.map(postDto,PostEntity.class);
        PostEntity saveEntity = postRepo.save(postEntity);
        log.info("Successfully create a post.");
        return modelMapper.map(saveEntity,PostDto.class);
    }


    @CachePut(cacheNames = CACHE_NAME, key = "#result.id")
    public PostDto updateData(PostDto input, Long id) {
        PostEntity olderData = postRepo.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("post is not present with id "+id)
        );

        if(!olderData.getTitle().equals(input.getTitle())){
            log.error("Attempted to change title of post.");
            throw new RuntimeException("Attempted to change title of post.");
        }

        input.setId(id);
        modelMapper.map(input,olderData);
        PostEntity saveData  = postRepo.save(olderData);
        log.info("Successfully update the post.");
        return modelMapper.map(saveData,PostDto.class);
    }

    @Cacheable(cacheNames = CACHE_NAME, key = "#id")
    public PostDto getData(Long id) {
        log.info("calling get post by id.");
        PostEntity postEntity = postRepo.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("post is not present with id "+id)
        );
        log.info("Successfully get the post.");
        return modelMapper.map(postEntity,PostDto.class);
    }
}
