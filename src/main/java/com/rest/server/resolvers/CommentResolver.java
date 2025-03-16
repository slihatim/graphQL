package com.rest.server.resolvers;

import com.rest.server.models.Comment;
import com.rest.server.services.CommentService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class CommentResolver {
    @Autowired
    private CommentService commentService;

    @QueryMapping
    public List<Comment> getAllComments(@Argument int page, @Argument int limit){
        Page<Comment> pageResult = commentService.allComments(PageRequest.of(page, limit));
        return pageResult.getContent();
    }

    @QueryMapping
    public Comment getSingleComment(@Argument String id){
        return commentService.singleComment(id).get();
    }

    @QueryMapping
    public List<Comment> getCommentsByPost(@Argument String postId, @Argument int page, @Argument int limit){
        Page<Comment> pageResult = commentService.findCommentsByPostId(postId, page, limit);
        return pageResult.getContent();
    }

    @QueryMapping
    public List<Comment> getCommentsByUser(@Argument String userId, @Argument int page, @Argument int limit){
        Page<Comment> pageResult = commentService.findCommentsByUserId(userId, page, limit);
        return pageResult.getContent();
    }

    @MutationMapping
    public Comment createComment(@Argument String commentMessage,@Argument String commentOwnerId,@Argument String commentPostId,@Argument String commentPublishDate){
        Comment comment = new Comment();
        comment.setCommentMessage(commentMessage);
        comment.setCommentOwnerId(commentOwnerId);
        comment.setCommentPostId(commentPostId);
        comment.setCommentPublishDate(commentPublishDate);
        return commentService.createComment(comment);
    }

    @MutationMapping
    public Comment updateComment(@Argument String id, @Argument String commentMessage){
        Comment comment = new Comment();
        comment.setCommentMessage(commentMessage);
        return commentService.updateComment(id, comment);
    }

    @MutationMapping
    public String deleteComment(@Argument String id){
        commentService.deleteComment(id);
        return id;
    }

}
