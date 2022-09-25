package edu.mum.cs544.user.service;

import edu.mum.cs544.user.dto.UserLogin;
import edu.mum.cs544.user.dto.UserRequest;
import edu.mum.cs544.user.dto.UserResponse;

public interface UserService {
    UserResponse getAll();
    UserResponse getById(Integer id);
    UserResponse addUser(UserRequest userRequest);
    UserResponse deleteById(Integer id);
    UserResponse updateUser(Integer id, UserRequest userRequest);
    UserResponse getByUsername(UserLogin userLogin);
}
