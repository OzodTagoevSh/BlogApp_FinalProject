package edu.mum.cs544.user.service.imp;

import edu.mum.cs544.user.dto.UserDto;
import edu.mum.cs544.user.dto.UserLogin;
import edu.mum.cs544.user.dto.UserRequest;
import edu.mum.cs544.user.dto.UserResponse;
import edu.mum.cs544.user.entity.Role;
import edu.mum.cs544.user.entity.User;
import edu.mum.cs544.user.repository.RoleRepository;
import edu.mum.cs544.user.repository.UserRepository;
import edu.mum.cs544.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserResponse getAll() {
        return new UserResponse(true, userRepository.findAll());
    }

    @Override
    public UserResponse getById(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null)
            return new UserResponse(false, "User not found", null);
        return new UserResponse(true, modelMapper.map(user, UserDto.class));
    }

    @Override
    public UserResponse addUser(UserRequest userRequest) {
        Role role = roleRepository.findByName(userRequest.getRoleName());
        if (role == null)
            return new UserResponse(false, "Role not found", null);
        if(userRepository.existsByUsername(userRequest.getUsername()))
            return new UserResponse(false, "Username has already taken!", null);
        User user = userRepository.save(new User(userRequest.getUsername(), userRequest.getFirstname(), userRequest.getLastname(),
                userRequest.getPassword(), Collections.singleton(role)));
        return new UserResponse(true, "User saved successfully!", modelMapper.map(user, UserDto.class));
    }

    @Override
    public UserResponse deleteById(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null)
            return new UserResponse(false, "User not found", null);
        userRepository.delete(user);
        return new UserResponse(true, "User deleted successfully", null);
    }

    @Override
    public UserResponse updateUser(Integer id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null)
            return new UserResponse(false, "User not found!", null);
        user.setUsername(userRequest.getUsername());
        user.setFirstname(userRequest.getFirstname());
        user.setLastname(userRequest.getLastname());
        user.setPassword(userRequest.getPassword());
        userRepository.save(user);
        return new UserResponse(true, "User updated successfully!", null);
    }

    @Override
    public UserResponse getByUsername(UserLogin userLogin) {
        User user = userRepository.findByUsername(userLogin.getUsername());
        if (user == null)
            return new UserResponse(false, "User not found!", null);
        if(!(user.getPassword().equals(userLogin.getPassword())))
            return new UserResponse(false, "Bad credentials!", null);
        return new UserResponse(true, "User found!", modelMapper.map(user, UserDto.class));
    }
}
