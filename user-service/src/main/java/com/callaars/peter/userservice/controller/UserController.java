package com.callaars.peter.userservice.controller;

import com.callaars.peter.userservice.dto.UserDto;
import com.callaars.peter.userservice.dto.UserDtoMapper;
import com.callaars.peter.userservice.model.User;
import com.callaars.peter.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    // GET http://localhost:8080/users/Peter.Callaars@gmail.com
    @GetMapping("/{email}")
    public UserDto getUserByEmail(@PathVariable
                                      @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
                                              String email) {

        User user = userService.getUserByEmail(email);

        return userDtoMapper.toDto(user);
    }

    @GetMapping
    public Page<UserDto> getAllUsersByPage(@PageableDefault(size = 20) Pageable pageable) {
        return userService.getAllUsersByPage(pageable)
                .map(userDtoMapper::toDto);
    }


    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        User user = userDtoMapper.toUser(userDto);
        User savedUser = userService.createUser(user);
        return userDtoMapper.toDto(savedUser);
    }

    @PutMapping
    public UserDto createOrUpdateUser(@RequestBody UserDto userDto) {
        User user = userDtoMapper.toUser(userDto);
        User savedUser = userService.createOrUpdateUser(user);
        return userDtoMapper.toDto(savedUser);
    }

    @DeleteMapping("{email}")
    public void deleteUserByEmail(@PathVariable
                                      @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
                                              String email) {
        userService.deleteUserByEmail(email);
    }
}

