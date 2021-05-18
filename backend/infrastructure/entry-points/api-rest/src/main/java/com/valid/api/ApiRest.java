package com.valid.api;

import java.util.List;
import java.util.stream.Collectors;

import com.valid.api.model.request.UserRequest;
import com.valid.api.model.response.UserResponse;
import com.valid.model.user.User;
import com.valid.usecase.user.UserUseCase;

import org.reactivecommons.utils.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/user", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {
    private final UserUseCase userUseCase;
    private final ObjectMapper mapper;

    @GetMapping()
    @CrossOrigin
    public ResponseEntity<List<UserResponse>> getAll() {
        List<User> users = this.userUseCase.getAll();
        List<UserResponse> response = users.stream().map(current -> {
            return this.mapper.map(current, UserResponse.class);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping()
    @CrossOrigin
    public ResponseEntity<UserResponse> save(@RequestBody UserRequest request) {
        User saved = this.userUseCase.save(this.mapper.map(request, User.class));
        return ResponseEntity.status(HttpStatus.OK).body(this.mapper.map(saved, UserResponse.class));
    }

    @PutMapping()
    @CrossOrigin
    public ResponseEntity<List<UserResponse>> update(@RequestBody List<UserRequest> request) {
        List<User> users = request.stream().map(current -> this.mapper.map(current, User.class))
                .collect(Collectors.toList());
        List<User> updated = this.userUseCase.update(users);
        List<UserResponse> response = updated.stream().map(current -> this.mapper.map(current, UserResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
