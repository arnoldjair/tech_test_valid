package com.valid.usecase.user;

import java.util.List;
import java.util.stream.Collectors;

import com.valid.exception.UserAlreadyExistsException;
import com.valid.model.user.User;
import com.valid.model.user.gateways.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserUseCase {

    private final UserRepository userRepository;

    public User save(User user) {
        if (Boolean.FALSE.equals(this.userRepository.exists(user))) {
            return this.userRepository.save(user);
        } else {
            throw new UserAlreadyExistsException(user.getFirstName(), user.getLastName());
        }
    }

    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    public List<User> update(List<User> users) {
        List<User> existing = this.userRepository
                .findAllById(users.stream().map(User::getId).collect(Collectors.toList()));

        existing = existing.stream().map(current -> {
            current.setProcessed(Boolean.TRUE);
            return current;
        }).collect(Collectors.toList());

        return this.userRepository.save(existing);
    }
}
