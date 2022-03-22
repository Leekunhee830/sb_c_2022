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
		
		makeTestData();
	}
	
	
	private void makeTestData() {
		for(int i=1; i<=10 ;i++) {
			int id=articlesLastId+1;
			String title="제목"+i;
			String body="내용"+i;
			
			writeArticle(title,body);
		}
	}
	
	public Article writeArticle(String title,String body) {
		int id=articlesLastId+1;
		Article article=new Article(id,title,body);
		
		articles.add(article);
		articlesLastId=id;
		
		return article;
	}
	
	private Article getArticle(int id) {
		for(Article article:articles) {
			if(article.getId()==id) {
				return article;
			}
		}
			
		return null;
	}
	
	private void deleteArticle(int id) {
		Article article=getArticle(id);
		articles.remove(article);
	}

	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title,String body) {
		Article article=writeArticle(title,body);
		
		return article;
	}
	
	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List getArticles() {
		return articles;
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		Article article=getArticle(id);
		
		if(article==null) {
			return id+"번 게시물이 존재하지 않습니다.";
		}
		
		deleteArticle(id);
		
		return id+"번 게시물을 삭제하였습니다.";
	}



	
}
