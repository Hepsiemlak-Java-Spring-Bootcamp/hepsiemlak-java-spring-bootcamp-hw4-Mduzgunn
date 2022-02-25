package com.example.realestate.service;

import com.example.realestate.dto.UpdateUserRequest;
import com.example.realestate.dto.UserRequest;
import com.example.realestate.dto.response.UserResponse;
import com.example.realestate.model.User;
import com.example.realestate.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    final
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserResponse convertToUserResponse(User savedUser) {
        UserResponse response = new UserResponse();
        response.setName(savedUser.getName());
        response.setBio(savedUser.getBio());
        response.setEmail(savedUser.getEmail());
        response.setPhoto(savedUser.getPhoto());
        return response;
    }

    private User convertToUser(UserRequest request) {
        User user = null;
        user = new User();
        user.setUserType(user.getUserType());
        user.setBio(request.getBio());
        user.setName(request.getName());
        return user;
    }

    public List<UserResponse> getAllUsers() {
        List<UserResponse> userList = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            userList.add(convertToUserResponse(user));
        }
        return userList;
    }
    public UserResponse saveUser(UserRequest request) {
        User user = convertToUser(request);
        try {
            if (user == null) {
                throw new Exception("kullanıcı kaydedilemedi");
            }
            User savedUser = userRepository.save(user);
            return convertToUserResponse(savedUser);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public UserResponse updateUser(int id, UpdateUserRequest updateUserRequest) {
        UserResponse user = getUserById(id);
        User updateUser = new User(
                updateUserRequest.getUserType(),
                updateUserRequest.getName(),
                updateUserRequest.getEmail()
        );
        return convertToUserResponse(userRepository.save(updateUser));
    }

    public UserResponse getUserById(int userId) {
        Optional<User> user = userRepository.findById(userId);
        return convertToUserResponse(user.get());
    }
    public String deleteUserById(int id) {
        getUserById(id);
        userRepository.deleteById(id);
        return "user deleted successfully "+id;
    }


}
