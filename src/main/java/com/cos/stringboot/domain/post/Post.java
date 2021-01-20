package com.cos.stringboot.domain.post;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.Data;

@Data
@Entity	//javax 꺼
public class Post {
	@Id		//프라이머리키 지정
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//데이터베이스에 사용되는 번호 증가 전략값을 따라라 (데베마다 sequency, auto increment인데 그냥 identity를 쓰면됨)
	private int id;
	private String title;
	private String content;
	private int readCount;	//Dialect 방언 mySql : read_count
	
}
