package com.lkh.example.demo.service;


import org.springframework.stereotype.Service;
import com.lkh.example.demo.repository.BoardRepository;
import com.lkh.example.demo.vo.Board;


@Service
public class BoardService {
	private BoardRepository boardRepository;
	
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository=boardRepository;
	}
	
	public Board getBoardById(int id) {
		return boardRepository.getBoardById(id);
	}
}
