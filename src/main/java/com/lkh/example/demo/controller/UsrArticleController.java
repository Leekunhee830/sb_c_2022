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
import com.lkh.example.demo.util.Ut;
import com.lkh.example.demo.vo.Article;
import com.lkh.example.demo.vo.ResultData;

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
	public ResultData<Article> doAdd(String title,String body) {
		if(Ut.empty(title)) {
			return ResultData.from("F-1", "title(을)를 입력해주세요.");
		}
		if(Ut.empty(body)) {
			return ResultData.from("F-1", "body(을)를 입력해주세요.");
		}
		
		ResultData<Integer> writeArticleRd=articleService.writeArticle(title,body);
		int id=writeArticleRd.getData1();
		Article article=articleService.getArticle(id);
		
		return ResultData.newData(writeArticleRd,article);
	}
	
	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public ResultData<List<Article>> getArticles() {
		List<Article> articles= articleService.getArticles();
		return ResultData.from("S-1", "게시물 리스트 입니다.",articles);
	}
	
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData<Article> getArticle(int id) {
		Article article=articleService.getArticle(id);
		if(article==null) {
			return ResultData.from("F-1",Ut.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		
		return ResultData.from("S-1",Ut.f("%d번 게시물입니다.", id),article);
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData<Article> doDelete(int id) {
		Article article=articleService.getArticle(id);
		
		if(article==null) {
			return ResultData.from("F-1",Ut.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		
		articleService.deleteArticle(id);
		return ResultData.from("S-1",Ut.f("%d번 게시물을 삭제하였습니다.", id),article);
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData<Article> doModify(int id,String title,String body) {
		Article article=articleService.getArticle(id);
		
		if(article==null) {
			return ResultData.from("F-1",Ut.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		
		articleService.modifyArticle(id,title,body);
		
		return ResultData.from("S-1",Ut.f("%d번 게시물을 수정하였습니다.", id),article);
	}

	
	
}
