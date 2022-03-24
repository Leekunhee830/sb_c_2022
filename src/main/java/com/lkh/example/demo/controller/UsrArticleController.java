package com.lkh.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lkh.example.demo.service.ArticleService;
import com.lkh.example.demo.vo.Article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Controller
public class UsrArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	

	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title,String body) {
		Article article=articleService.writeArticle(title,body);
		
		return article;
	}
	
	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List getArticles() {
		return articleService.getArticles();
	}
	
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public Object getArticleAction(int id) {
		Article article=articleService.getArticle(id);
		if(article==null) {
			return id+"번 게시물은 존재하지 않습니다.";
		}
		
		return article;
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		Article article=articleService.getArticle(id);
		
		if(article==null) {
			return id+"번 게시물이 존재하지 않습니다.";
		}
		
		articleService.deleteArticle(id);
		
		return id+"번 게시물을 삭제하였습니다.";
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id,String title,String body) {
		Article article=articleService.getArticle(id);
		
		if(article==null) {
			return id+"번 게시물이 존재하지 않습니다.";
		}
		
		articleService.modifyArticle(id,title,body);
		
		return id+"번 게시물을 수정하였습니다.";
	}

	
	
}
