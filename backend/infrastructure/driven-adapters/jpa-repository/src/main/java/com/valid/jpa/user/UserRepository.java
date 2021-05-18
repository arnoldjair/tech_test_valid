package com.valid.jpa.user;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface UserRepository extends CrudRepository<UserData, UUID>, QueryByExampleExecutor<UserData> {
    UserData findTopByFirstNameAndLastName(String firstName, String lastName);
    Boolean existsByFirstNameAndLastName(String firstName, String lastName);
}
