package com.tqgt.gtblog.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tqgt.gtblog.model.UserModel;

public interface UserRepository extends MongoRepository<UserModel, String>{
	Optional<UserModel> findByUsername(String username);
	
	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
