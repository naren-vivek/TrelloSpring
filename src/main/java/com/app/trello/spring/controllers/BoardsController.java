package com.app.trello.spring.controllers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.trello.spring.bo.ResultObject;
import com.app.trello.spring.dto.Board;
import com.app.trello.spring.exceptions.BoardNotFoundException;
import com.app.trello.spring.service.BoardsService;

@RestController
@RequestMapping("/boards")
public class BoardsController {

	@Autowired

	private BoardsService boardsService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<ResultObject> index() {
		List<Board> boardsList = boardsService.getBoards();
		ResultObject resultObject = new ResultObject();
		HttpStatus httpStatus = HttpStatus.OK;

		if (boardsList != null) {
			resultObject.setResultObj(boardsList);
		} else {
			httpStatus = HttpStatus.BAD_REQUEST;
			resultObject.setErrorMessage("Something went wrong");
		}

		resultObject.setHttpStatus(httpStatus.name());
		return new ResponseEntity<ResultObject>(resultObject, httpStatus);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{boardId}")
	public ResponseEntity<Board> show(@PathVariable String boardId) throws BoardNotFoundException {
		Board board = boardsService.getBoard(boardId);
		return new ResponseEntity<Board>(board, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Board> create(@RequestBody Board board) {
		Board newBoard = boardsService.createBoard(board);

		if (newBoard != null) {
			return new ResponseEntity<Board>(newBoard, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Board>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{boardId}")
	public ResponseEntity<Board> update(@PathVariable String boardId, @RequestBody Board board) {
		Board updatedBoard = boardsService.updateBoard(board);

		if (updatedBoard != null) {
			return new ResponseEntity<Board>(updatedBoard, HttpStatus.OK);
		} else {
			return new ResponseEntity<Board>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{boardId}")
	public ResponseEntity<ResultObject> delete(@PathVariable String boardId) {
		boolean deleted = boardsService.deleteBoard(boardId);
		ResultObject resultObject = new ResultObject();
		HttpStatus httpStatus = HttpStatus.OK;

		if (deleted) {
			resultObject.setResultObj(new String("Deleted"));
		} else {
			httpStatus = HttpStatus.BAD_REQUEST;
			resultObject.setErrorMessage("Could not delete");
		}

		resultObject.setHttpStatus(httpStatus.name());
		return new ResponseEntity<ResultObject>(resultObject, httpStatus);
	}
}
