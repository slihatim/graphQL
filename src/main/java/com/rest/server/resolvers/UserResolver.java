package com.rest.server.resolvers;

import com.rest.server.models.User;
import com.rest.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserResolver {
    @Autowired
    private UserService userService;

    @QueryMapping
    public List<User> allUsers(@Argument int page, @Argument int limit){
        Page<User> pageResult = userService.allUsers(PageRequest.of(page, limit));
        return pageResult.getContent();
    }

    @QueryMapping
    public User singleUser(@Argument String id){
        return userService.singleUser(id).get();
    }

    @QueryMapping
    public List<User> searchUsers(@Argument String query, @Argument int page, @Argument int limit){
        Page<User> pageResult = userService.searchUsers(query, PageRequest.of(page, limit));
        return pageResult.getContent();
    }

    @MutationMapping
    public User createUser( @Argument String userTitle , @Argument String userFirstName , @Argument String userLastName , @Argument String userGender , @Argument String userEmail , @Argument String userPassword , @Argument String userDateOfBirth , @Argument String userRegisterDate , @Argument String userPhone , @Argument String userPicture , @Argument String userLocationId){
        User user = new User();
        user.setUserTitle(userTitle);
        user.setUserFirstName(userFirstName);
        user.setUserLastName(userLastName);
        user.setUserGender(userGender);
        user.setUserEmail(userEmail);
        user.setUserPassword(userPassword);
        user.setUserDateOfBirth(userDateOfBirth);
        user.setUserRegisterDate(userRegisterDate);
        user.setUserPhone(userPhone);
        user.setUserPicture(userPicture);
        user.setUserLocationId(userLocationId);

        return userService.createUser(user);
    }

    @MutationMapping
    public User updateUser(@Argument String id, @Argument String userTitle , @Argument String userFirstName , @Argument String userLastName , @Argument String userPassword , @Argument String userDateOfBirth  , @Argument String userPhone , @Argument String userPicture , @Argument String userLocationId){
        User user = new User();
        user.setUserTitle(userTitle);
        user.setUserFirstName(userFirstName);
        user.setUserLastName(userLastName);
        user.setUserPassword(userPassword);
        user.setUserDateOfBirth(userDateOfBirth);
        user.setUserPhone(userPhone);
        user.setUserPicture(userPicture);
        user.setUserLocationId(userLocationId);

        return userService.updateUser(id, user);
    }

    @MutationMapping
    public String deleteUser(@Argument String id){
        userService.deleteUser(id);
        return id;
    }
}
