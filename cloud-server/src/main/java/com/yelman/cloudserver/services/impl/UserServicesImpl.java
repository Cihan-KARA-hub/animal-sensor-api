package com.yelman.cloudserver.services.impl;

import com.yelman.cloudserver.model.Users;

public interface UserServicesImpl {

    boolean registerUser(Users user);

    Users getUserByUsername(String username);

    Users getUserByEmail(String email);

    Users getUserById(Long id);

    void updateUser(Users user);

    void deleteUser(Long id );

}
