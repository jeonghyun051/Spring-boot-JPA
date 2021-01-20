package com.cos.stringboot.web;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.stringboot.domain.post.Post;
import com.cos.stringboot.service.PostService;

//클라이언트 http 요청시에 @controller, @RestController가 붙은 클래스가 메모리에 로딩된다. 그 영역이 IoC Container이다
@Controller
public class PostController {
	
	//DI (Dependency Injection) 한번 메모리에 띄운것을 사용
	private PostService postService;
	
	public PostController(PostService postService) {
		System.out.println("@Controller");
	this.postService = postService;
	}
	
	//Model 객체 제공
							// produces응답을 뭘로할지 데이터 타입을 정할수 있음, 얘를 걸때 조건은 @Controller이여야함
	@GetMapping("/post") 	// name을 생략하고 "/"만 적어도 됨 name은 default
	public String findAll(Model model) {	//컨트롤러의 함수의 파라메터는 톰켓이 가지고 있는 객체 + ioc컨테이너가 가지고 있는 객체 HttpServletRequest request 사용가능
		//1. DB에 데이터 가져오기
		//2. request에 값 담기
		List<Post> posts = postService.글목록();
		System.out.println(posts);
		model.addAttribute("posts", posts);
		//request.setAttribute("posts", posts);
		return "post/list";	//3. RequestDispacher 사용 자동으로 동작함 return에서 
	}
	
	@GetMapping("/post/{id}")
	public String findById(@PathVariable int id, Model model) {	//들어온 숫자를 id에 바인딩 할 수 있음
		
		model.addAttribute("post", postService.글상세보기(id));
		return "post/detail";
	}
	
	@PostMapping("/post")
	public String save(Post post) {	//x-www-form-urlencoded 맵핑해서 object로 변환해줌 json으로 데이터 던지면 못받음 (폼태그 던지기)
		System.out.println("post: " + post );
		
//		PostService service = new PostService();	//요청시 마다 뜨는거 서버 실행될때 메모리에 한번만 띄우고싶다. 여기서 new를 하면안됨 싱글톤으로 만들어야 함
		Post postEntity = postService.글저장(post);
		System.out.println(postEntity);
		return "redirect:/post";	//response.sendRedirect 세이브를 때리면 리스트 페이지로 이동하는데 값이 없음, 값을 일일이 추가하기 보다는 위에 post로 보내는것
	}
	
}
