package com.example.productionreadyfeatures.Controller;

import com.example.productionreadyfeatures.DTO.PostDto;
import com.example.productionreadyfeatures.Service.PostService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public PostDto addData(@RequestBody PostDto postDto){
        return postService.addData(postDto);
    }

    @GetMapping(path = "/{id}")
    public PostDto getData(@PathVariable Long id){
        return postService.getData(id);
    }

    @PutMapping("/{id}")
    public PostDto updateData(@RequestBody PostDto postDto,@PathVariable Long id){
        return postService.updateData(postDto,id);
    }
}
