package com.valid.jpa.user;

import java.util.List;
import java.util.UUID;

import com.valid.jpa.helper.AdapterOperations;
import com.valid.model.user.User;
import com.valid.model.user.gateways.UserRepository;

import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryAdapter extends AdapterOperations<User, UserData, UUID, com.valid.jpa.user.UserRepository>
        implements UserRepository {

    public UserRepositoryAdapter(com.valid.jpa.user.UserRepository repository, ObjectMapper mapper) {
        /**
         * Could be use mapper.mapBuilder if your domain model implement builder pattern
         * super(repository, mapper, d ->
         * mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build()); Or using
         * mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.mapBuilder(d, User.UserBuilder.class).build());
    }

    @Override
    public Boolean exists(User user) {
        return this.repository.existsByFirstNameAndLastName(user.getFirstName(), user.getLastName());
    }

    @Override
    public List<User> save(List<User> users) {
        return this.saveAllEntities(users);
    }

    @Override
    public List<User> findAllById(List<UUID> ids) {
        return this.toList(this.repository.findAllById(ids));
    }
}
