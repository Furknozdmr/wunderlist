package com.furkanozdemir.adapter.user.repository;

import com.furkanozdemir.adapter.user.entity.Users;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CouchbaseRepository<Users, String> {

    Optional<Users> findByEmail(String email);
}
