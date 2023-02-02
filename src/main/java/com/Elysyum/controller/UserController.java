package com.Elysyum.controller;

import com.Elysyum.dto.UserDto;
import com.Elysyum.model.User;
import com.Elysyum.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private UserService userService;


    @GetMapping("/{id}")
    public UserDto findById(@PathVariable long id ){

        User user = userService.findById(id);
        return new UserDto(id,user.getUsername(), user.getPassword(), user.getEmail());
    }

    @GetMapping
    public List<UserDto> findAll(){

        return userService.findAll().stream().map
                (user -> modelMapper.map(user,UserDto.class)).toList();
    }

    @PostMapping
    public ResponseEntity<UserDto> register (@Valid @RequestBody UserDto userDto) throws URISyntaxException{

        if (userService.findByUsername(userDto.getUsername())){
            return  ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        userService.save(userDto);
        System.out.println("Successful registration!" + userDto.getUsername());
        return ResponseEntity.created(new URI("/users/" + userDto.getId())).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable ("id") long id ,@RequestBody UserDto user){
        System.out.println("Update user ID" + id);
        return new ResponseEntity<>(userService.updateUser(id,user),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@Valid @PathVariable("id") long id ){
        userService.deleteUser(id);
        System.out.println("Deleted user with id" + id);
        return ResponseEntity.ok().build();

    }
}
