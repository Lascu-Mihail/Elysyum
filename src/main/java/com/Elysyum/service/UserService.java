package com.Elysyum.service;

import com.Elysyum.dto.UserDto;
import com.Elysyum.model.User;
import com.Elysyum.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;


@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserService {

    private static final Logger log = getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public void save(UserDto userDto) {

        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        log.info("Saving new user");

        userRepository.save(user);

    }

    public List<User> findAll() {
        log.info("finding all users");
        return userRepository.findAll();
    }

    public User findById(Long id) {
        log.info("Finding by id");
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "User with id " + id + " not found"));
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public boolean findByUsername(String username) {

        return userRepository.findByUsername(username).isPresent();

    }

    public User updateUser(Long userId,UserDto userDetails){

        User user = userRepository.findById(userId).get();
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setEmail(userDetails.getEmail());

        return userRepository.save(user);

    }


}
