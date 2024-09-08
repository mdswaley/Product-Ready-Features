package com.example.productionreadyfeatures.Service;

import com.example.productionreadyfeatures.DTO.PostDto;
import com.example.productionreadyfeatures.Entity.PostEntity;
import com.example.productionreadyfeatures.Repository.PostRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {
    private final PostRepo postRepo;
    private final ModelMapper modelMapper;

    public PostService(PostRepo postRepo, ModelMapper modelMapper) {
        this.postRepo = postRepo;
        this.modelMapper = modelMapper;
    }

    public PostDto addData(PostDto postDto){
        PostEntity postEntity = modelMapper.map(postDto,PostEntity.class);
        PostEntity saveEntity = postRepo.save(postEntity);
        return modelMapper.map(saveEntity,PostDto.class);
    }


    public PostDto updateData(PostDto input, Long id) {
        PostEntity olderData = postRepo.findById(id).orElse(null);
        input.setId(id);
        modelMapper.map(input,olderData);
        PostEntity saveData  = postRepo.save(olderData);

        return modelMapper.map(saveData,PostDto.class);



    }

    public PostDto getData(Long id) {
        PostEntity postEntity = postRepo.findById(id).orElse(null);
        return modelMapper.map(postEntity,PostDto.class);
    }
}
