package com.rest.server.resolvers;

import com.rest.server.models.Post;
import com.rest.server.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PostResolver {
    @Autowired
    private PostService postService;

    @QueryMapping
    public List<Post> allPosts(@Argument int page, @Argument int limit){
        Page<Post> pageResult = postService.allPosts(PageRequest.of(page, limit));
        return pageResult.getContent();
    }

    @QueryMapping
    public List<Post> findPostsByUserId(@Argument String userId, @Argument int page, int limit){
        Page<Post> pageResult = postService.findPostsByUserId(userId, page, limit);
        return pageResult.getContent();
    }

    @QueryMapping
    public List<Post> findPostsByTag(@Argument String tagName, @Argument int page, int limit){
        Page<Post> pageResult = postService.findPostsByTag(tagName, page, limit);
        return pageResult.getContent();
    }

    @QueryMapping
    public Post singlePost(@Argument String id){
        return postService.singlePost(id).get();
    }

    @QueryMapping
    public List<Post> searchPosts(@Argument String query, @Argument int page, int limit){
        Page<Post> pageResult = postService.searchPosts(query, PageRequest.of(page, limit));
        return pageResult.getContent();
    }

    @MutationMapping
    public Post createPost(@Argument String postText, @Argument String postImage, @Argument int postLikes, @Argument String postLink, @Argument List<String> postTags, @Argument String postPublishDate, @Argument String postOwnerId){
        Post p = new Post();
        p.setPostText(postText);
        p.setPostImage(postImage);
        p.setPostLikes(postLikes);
        p.setPostLink(postLink);
        p.setPostTags(postTags);
        p.setPostPublishDate(postPublishDate);
        p.setPostOwnerId(postOwnerId);
        return postService.createPost(p);
    }

    @MutationMapping
    public Post updatePost(@Argument String id, @Argument String postText, @Argument String postImage, @Argument int postLikes, @Argument String postLink, @Argument List<String> postTags){
        Post p = new Post();
        p.setPostText(postText);
        p.setPostImage(postImage);
        p.setPostLikes(postLikes);
        p.setPostLink(postLink);
        p.setPostTags(postTags);
        return postService.updatePost(id, p);
    }

    @MutationMapping
    public String deletePost(@Argument String id){
        postService.deletePost(id);
        return id;
    }
}
