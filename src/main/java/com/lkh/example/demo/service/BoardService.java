package com.lkh.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lkh.example.demo.repository.ArticleRepository;
import com.lkh.example.demo.repository.BoardRepository;
import com.lkh.example.demo.util.Ut;
import com.lkh.example.demo.vo.Article;
import com.lkh.example.demo.vo.Board;
import com.lkh.example.demo.vo.ResultData;

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
