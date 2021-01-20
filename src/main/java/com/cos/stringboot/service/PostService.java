package com.cos.stringboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cos.stringboot.domain.post.Post;
import com.cos.stringboot.domain.post.PostRepository;

//@Controller, @RestController, @Service, @Configuration, @Component 

@Service	//서버 실행시에 IoC에 등록 타입을 PostService으로 등록해준다.
public class PostService {
	
	private PostRepository postRepository;	
	
	public PostService(PostRepository postRepository) {	
		this.postRepository = postRepository;
		System.out.println("PostService 실행됨");
	}
	
	public Post 글상세보기(int id) {
		
		return postRepository.findById(id).orElse(new Post());
	}
	
	public List<Post> 글목록(){
		
		return postRepository.findAll();
	}
	
	public Post 글저장(Post post) {
//		Post postEntity = postRepository.save(post);
//		return postEntity;	//길게 적지말고 밑에처럼 가능
		return postRepository.save(post);
	}
}
