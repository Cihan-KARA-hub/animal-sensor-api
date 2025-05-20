package com.yelman.cloudserver.api;

import com.yelman.cloudserver.api.dto.LoginRequestDto;
import com.yelman.cloudserver.api.dto.UsersCreateDto;
import com.yelman.cloudserver.model.Users;
import com.yelman.cloudserver.services.impl.UserServicesImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:50140")
@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserServicesImpl userServices;

    public UserController(UserServicesImpl userServices) {
        this.userServices = userServices;
    }


    @PostMapping("")
    public HttpStatus addUser(@RequestBody UsersCreateDto user) {
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
    public ResponseEntity<Long> login(@RequestBody LoginRequestDto dto) {
        Long companyId = userServices.loginUserAndGetCompanyId(dto.getUsername(), dto.getPassword());
        if (companyId != null) {
            return ResponseEntity.ok(companyId);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
