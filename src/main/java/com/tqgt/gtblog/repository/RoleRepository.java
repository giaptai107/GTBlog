package com.tqgt.gtblog.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tqgt.gtblog.model.ERole;
import com.tqgt.gtblog.model.RoleModel;

public interface RoleRepository extends MongoRepository<RoleModel, String>{
	Optional<RoleModel> findByName(ERole name);
}
