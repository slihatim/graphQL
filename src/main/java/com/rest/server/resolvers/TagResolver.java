package com.rest.server.resolvers;

import com.rest.server.models.Tag;
import com.rest.server.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TagResolver {
    @Autowired
    private TagService tagService;

    @QueryMapping
    public List<Tag> allTags(@Argument int page, @Argument int limit){
        Page<Tag> pageResult = tagService.allTags(PageRequest.of(page, limit));
        return pageResult.getContent();
    }

    @QueryMapping
    public Tag singleTag(@Argument String id){
        return tagService.singleTag(id).get();
    }

    @MutationMapping
    public Tag createTag(@Argument String tagName){
        Tag t = new Tag();
        t.setTagName(tagName);
        return tagService.createTag(t);
    }

    @MutationMapping
    public Tag updateTag(@Argument String id, @Argument String tagName){
        Tag t = new Tag();
        t.setTagName(tagName);
        return tagService.updateTag(id, t);
    }

    @MutationMapping
    public String deleteTag(@Argument String id){
        tagService.deleteTag(id);
        return id;
    }

}
