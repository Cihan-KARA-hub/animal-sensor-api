package com.yelman.cloudserver.api;

import com.yelman.cloudserver.api.dto.LoginRequestDto;
import com.yelman.cloudserver.model.Users;
import com.yelman.cloudserver.services.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }


    @PostMapping("")
    public HttpStatus addUser(@RequestBody Users user) {
        boolean empty = userServices.registerUser(user);
        if (empty) {
            return HttpStatus.CREATED;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @GetMapping("")
    public List<Users> getUsers() {
        return userServices.getAllUser();
    }

    @PostMapping("/login")
    public HttpStatus login(@RequestBody LoginRequestDto dto) {
        boolean login = userServices.loginUserBoolean(dto.getUsername(), dto.getPassword());
        if (login) {
            return HttpStatus.OK;
        }
        return HttpStatus.UNAUTHORIZED;
    }
}
