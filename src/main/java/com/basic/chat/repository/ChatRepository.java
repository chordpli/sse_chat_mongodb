package com.basic.chat.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;

import com.basic.chat.domain.entity.Chat;

import reactor.core.publisher.Flux;

public interface ChatRepository extends ReactiveMongoRepository<Chat, String> {

	@Tailable // 커서를 안닫고 유지 -> DB에서 찾고 난 후, mongoDB에 한 건이라도 있었다면 닫지 않고 유지.
	@Query("{sender: ?0, receiver: ?1}")
	Flux<Chat> mFindBySender(String sender, String receiver);
	// Flux, response를 유지하면서 데이터를 계속 교환
}
