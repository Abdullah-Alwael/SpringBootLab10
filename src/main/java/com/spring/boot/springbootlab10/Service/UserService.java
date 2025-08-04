package com.spring.boot.springbootlab10.Service;

import com.spring.boot.springbootlab10.Model.User;
import com.spring.boot.springbootlab10.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public Boolean updateUser(Integer userId, User user){
        User oldUser = userRepository.getById(userId);

        if (oldUser == null){
            return false; // user does not exist
        }

        oldUser.setName(user.getName());
        oldUser.setAge(user.getAge());
        oldUser.setRole(user.getRole());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());

        userRepository.save(oldUser);
        return true; // user updated
    }

    public Boolean deleteUser(Integer userId){
        User oldUser = userRepository.getById(userId);

        if (oldUser == null){
            return false; // user does not exist
        }

        userRepository.delete(oldUser);
        return true; // user deleted
    }

}
