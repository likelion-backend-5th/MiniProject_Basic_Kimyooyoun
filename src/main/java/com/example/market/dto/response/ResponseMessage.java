package com.example.market.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {
	SUCCESS_ITEM_CREATE("등록이 완료되었습니다.");
	private final String message;
}
