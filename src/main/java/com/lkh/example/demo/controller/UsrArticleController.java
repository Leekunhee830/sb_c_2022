package com.lkh.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lkh.example.demo.vo.Article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Controller
public class UsrArticleController {
	private List<Article> articles;
	private int articlesLastId;
	
	public UsrArticleController() {
		articlesLastId=0;
		articles=new ArrayList<Article>();
	}
	
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title,String body) {
		int id=articlesLastId+1;
		Article article=new Article(id,title,body);
		
		articles.add(article);
		articlesLastId=id;
		
		return article;
	}
	
	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List getArticles() {
		return articles;
	}
}
