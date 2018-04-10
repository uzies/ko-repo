package org.ko.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.ko.exception.UserNotExistException;
import org.ko.web.dto.User;
import org.ko.web.dto.UserQueryCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user")
@Api("用户服务")
public class UserController {

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    @ApiOperation("用户查询列表")
    public List<User> query(UserQueryCondition condition,
                            @PageableDefault(page = 1, size = 15, sort = "username,asc") Pageable pageable) {
        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getSort());
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    @GetMapping("{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    @ApiOperation("用户详细查询")
    public User getInfo (@ApiParam("用户ID") @PathVariable String id) {
//        throw new UserNotExistException("1");
        System.out.println("getInfo---");
        User user = new User();
        user.setUsername("K.O");
        return user;
    }

    @PostMapping
    public User create (@Valid @RequestBody User user/*, BindingResult errors*/) {

//        if (errors.hasErrors()) {
//            errors.getAllErrors().forEach(System.out::println);
//        }

        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());
        user.setId("1");
        return user;
    }

    @PutMapping("{id:\\d+}")
    public User update (@Valid @RequestBody User user, BindingResult errors) {

        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(e -> {
                FieldError error = FieldError.class.cast(e);
                String message = error.getField() + ": " + e.getDefaultMessage();
                System.out.println(message);
            });
        }

        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());
        user.setId("1");
        return user;
    }

    @DeleteMapping("{id:\\d+}")
    public void delete (@PathVariable String id) {
        System.out.println(id);
    }

}