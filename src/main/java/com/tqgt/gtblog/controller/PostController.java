package com.tqgt.gtblog.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tqgt.gtblog.model.PostModel;
import com.tqgt.gtblog.repository.PostRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class PostController {
	@Autowired
	PostRepository postRepository;

	@GetMapping("/posts")
	public ResponseEntity<List<PostModel>> getAllPosts(@RequestParam(required = false) String title) {
		try {
			List<PostModel> listPosts = new ArrayList<PostModel>();
			if (title == null) {
				postRepository.findAll().forEach(listPosts::add);
			} else {
				postRepository.findByTitle(title).forEach(listPosts::add);
			}
			if (listPosts.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(listPosts, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/post/{id}")
	public ResponseEntity<PostModel> getPostById(@PathVariable("id") String id) {
		Optional<PostModel> post = postRepository.findById(id);

		if (post.isPresent()) {
			return new ResponseEntity<>(post.get(), HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/post")
	public ResponseEntity<PostModel> createPost(@RequestBody PostModel post) {
		try {
			PostModel newPost = postRepository.save(post);
			return new ResponseEntity<>(newPost, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/post/{id}")
	public ResponseEntity<PostModel> updatePost(@PathVariable("id") String id, @RequestBody PostModel newPost) {
		Optional<PostModel> post = postRepository.findById(id);

		if (post.isPresent()) {
			PostModel _post = post.get();
			_post.setTitle(newPost.getTitle());
			_post.setDescription(newPost.getDescription());
			return new ResponseEntity<>(postRepository.save(_post), HttpStatus.OK);

		} else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deletePost(@PathVariable("id") String id) {
		try {
			postRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
