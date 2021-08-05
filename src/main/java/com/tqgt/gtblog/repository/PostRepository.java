package com.tqgt.gtblog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tqgt.gtblog.model.PostModel;

public interface PostRepository extends MongoRepository<PostModel, String>{
	Optional<PostModel> findById(String id);

	List<PostModel> findByTitle(String title);
}
