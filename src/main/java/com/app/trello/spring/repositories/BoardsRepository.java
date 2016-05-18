package com.app.trello.spring.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.trello.spring.dto.Board;

public interface BoardsRepository extends MongoRepository<Board, String> {

}
