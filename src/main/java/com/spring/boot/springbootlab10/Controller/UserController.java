package com.spring.boot.springbootlab10.Controller;

import com.spring.boot.springbootlab10.Api.ApiResponse;
import com.spring.boot.springbootlab10.Model.User;
import com.spring.boot.springbootlab10.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@Valid @RequestBody User user, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
                    ApiResponse(errors.getFieldError().getDefaultMessage()));
        }

        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("User added successfully"));
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Integer userId, @Valid @RequestBody User user, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
                    ApiResponse(errors.getFieldError().getDefaultMessage()));
        }

        if (userService.updateUser(userId, user)){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("User updated successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Error, user does not exist"));
        }
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId){
        if (userService.deleteUser(userId)){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("User deleted successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Error, user does not exist"));
        }
    }
}
