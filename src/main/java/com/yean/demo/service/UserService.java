package com.yean.demo.service;

import com.yean.demo.entity.User;
import com.yean.demo.model.BaseResponseModel;
import com.yean.demo.model.BaseResponseWithDataModel;
import com.yean.demo.model.UserModel;
import com.yean.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<BaseResponseWithDataModel> listUsers() {
        List<User> usersData = userRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseWithDataModel("success", 200, usersData));
    }

    public ResponseEntity<BaseResponseModel> createUser(UserModel payload) {
        User user = new User();
        user.setName(payload.getName());
        user.setAge(payload.getAge());
        user.setAddress(payload.getAddress());
        user.setEmail(payload.getEmail());
        user.setRole(payload.getRole());
        user.setCreateAt(LocalDateTime.now());

        userRepository.save(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new BaseResponseModel("User created successfully", 201));
    }

    public ResponseEntity<BaseResponseModel> updateUser(UserModel payload, Long userId) {
        Optional<User> existing = userRepository.findById(userId);

        // If the user exists, update the details
        if (existing.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new BaseResponseModel("User not found : " + userId, 404));
        }

        User updatedUser = existing.get();

        updatedUser.setEmail(payload.getEmail());
        updatedUser.setName(payload.getName());
        updatedUser.setAge(payload.getAge());
        updatedUser.setAddress(payload.getAddress());
        updatedUser.setRole(payload.getRole());

        userRepository.save(updatedUser);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new BaseResponseModel("User updated successfully", 200));
    }

    public ResponseEntity<BaseResponseModel> deleteUser(Long userId) {
        Optional<User> existing = userRepository.findById(userId);

        if (userRepository.existsById(userId)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new BaseResponseModel("User not found : " + userId, 404));
        }

        userRepository.deleteById(userId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(new BaseResponseModel("User deleted successfully", 204));
    }

    public ResponseEntity<BaseResponseWithDataModel> getUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);

        return user.map(value -> ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseWithDataModel("User found successfully", 200, value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new BaseResponseWithDataModel("User not found", 404, null)));

    }
}
