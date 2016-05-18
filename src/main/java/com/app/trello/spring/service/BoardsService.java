package com.app.trello.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

import com.app.trello.spring.dto.Board;
import com.app.trello.spring.exceptions.BoardNotFoundException;
import com.app.trello.spring.repositories.BoardsRepository;

@Service
public class BoardsService {

	@Autowired
	private BoardsRepository boardsRepository;

	public List<Board> getBoards() {
		List<Board> boardsList = boardsRepository.findAll();
		return boardsList;
	}

	public Board getBoard(String boardId) throws BoardNotFoundException, DataAccessResourceFailureException {
		Board board = boardsRepository.findOne(boardId);
		if (board == null) {
			throw new BoardNotFoundException();
		}

		return board;
	}

	public Board createBoard(Board board) {
		Board newBoard = null;
		newBoard = boardsRepository.
		return newBoard;
	}

	public Board updateBoard(Board board) {
		Board updatedBoard = null;
		updatedBoard = boardsRepository.save(board);
		return updatedBoard;
	}

	public boolean deleteBoard(String boardId) {
		boardsRepository.delete(boardId);
		return true;
	}
}
