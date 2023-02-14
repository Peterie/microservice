package com.callaars.peter.userservice.service;

import com.callaars.peter.userservice.model.User;
import com.callaars.peter.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User with email '@s' could not be found".formatted(email)
                ));
    }

    public Page<User> getAllUsersByPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }


    private int calculateAgeInYears(User user) {
        return Period.between(user.getBirthDate(), LocalDate.now()).getYears();
    }

    public Page<User> getYoungerUsers(Pageable pageable, int age) {
        List<User> users = userRepository.findAll()
                .stream().filter(user -> calculateAgeInYears(user) < age)
                .toList();
        return new PageImpl<>(users, pageable, users.size());
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User createOrUpdateUser(User user) {
        return userRepository.findByEmail(user.getEmail())
                .map(userToUpdate -> {
                    userToUpdate.setName(user.getName());
                    userToUpdate.setBirthDate(user.getBirthDate());
                    return userToUpdate;
                })
                .orElseGet(() -> userRepository.save(user));
    }

    public void deleteUserByEmail(String email) {
        userRepository.findByEmail(email)
                .ifPresentOrElse(userRepository::delete,
                        () -> {
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "User with email '@s' could not be found".formatted(email)
                    );
                });
    }
}
