package com.yean.demo.controller;

import com.yean.demo.model.BaseResponseModel;
import com.yean.demo.model.BaseResponseWithDataModel;
import com.yean.demo.model.UserModel;
import com.yean.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    //used for retrieving records
    @GetMapping()
    public ResponseEntity<BaseResponseWithDataModel> listUsers() {
        return userService.listUsers();
    }

    @GetMapping("/{user_Id}")
    public ResponseEntity<BaseResponseWithDataModel> getUser(@PathVariable("user_Id") Long userId) {
        return userService.getUser(userId);
    }

    @PostMapping
    public ResponseEntity<BaseResponseModel> createUser(@RequestBody UserModel payload) {
        return userService.createUser(payload);
    }

    //endpoint -> /api/v1/users/{user_id}
    @PutMapping("/{user_id}")
    public ResponseEntity<BaseResponseModel> updateUser(@PathVariable Long user_id, @RequestBody UserModel payload) {
        return userService.updateUser(payload,user_id);
    }

    //Path variable
    @DeleteMapping("/{userId}")
    public ResponseEntity<BaseResponseModel> deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }

}