package com.example.market.service;

import com.example.market.constants.ItemStatusType;
import com.example.market.entity.SalesItemEntity;
import com.example.market.exception.ApplicationException;
import com.example.market.exception.ErrorCode;
import com.example.market.repository.SalesItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SalesItemService {

	private final SalesItemRepository repository;

	@Transactional
	public void create(
			String title,
			String description,
			int minPrice,
			String writer,
			String password
	) {
		repository.save(
			SalesItemEntity.of(title, description, minPrice, ItemStatusType.ON_SALE, writer, password));
	}

	public void modify(
		Long salesItemId,
		String title,
		String description,
		int minPrice,
		String writer,
		String password
	){
		SalesItemEntity savedItem = repository.findById(salesItemId).orElseThrow( () ->
			new ApplicationException(ErrorCode.SALES_ITEM_NOT_FOUND));

		if(isValidPassword(password, savedItem)) {
			savedItem = SalesItemEntity.builder()
				.title(title)
				.description(description)
				.status(savedItem.getStatus())
				.minPrice(minPrice)
				.writer(writer)
				.password(password)
				.build();
			repository.saveAndFlush(savedItem);
		}
	}

	private boolean isValidPassword(String inputPassword, SalesItemEntity savedItem){
		return inputPassword.equals(savedItem.getPassword());
	}



}
