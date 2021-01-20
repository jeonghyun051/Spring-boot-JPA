package com.cos.stringboot.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;


//@Repository 왜냐하면 jpa 내부적으로 들어가보면 걸려있다. 생략가능함/ 인터페이스여서 에노테이션 안먹힘
public interface PostRepository extends JpaRepository<Post, Integer>{	//만들어진 dao를 가져온다. dao를 안만들어도 된다.

	
}
