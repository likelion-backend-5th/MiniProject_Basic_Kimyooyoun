package com.example.market.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentModifyRequest {
	private String writer;
	private String password;
	private String content;
}
