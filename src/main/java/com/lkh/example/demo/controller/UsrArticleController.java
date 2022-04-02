package com.lkh.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public ResultData<Article> doAdd(HttpSession httpSession, String title,String body) {
		boolean isLogined =false;
		int loginedMemberId=0;
		
		if(httpSession.getAttribute("loginedMemberId")!=null) {
			isLogined=true;
			loginedMemberId=(int)httpSession.getAttribute("loginedMemberId");
		}
		
		if(isLogined==false) {
			return ResultData.from("F-A", "로그인 후 이용해주세요.");
		}
		
		if(Ut.empty(title)) {
			return ResultData.from("F-1", "title(을)를 입력해주세요.");
		}
		if(Ut.empty(body)) {
			return ResultData.from("F-1", "body(을)를 입력해주세요.");
		}
		
		ResultData<Integer> writeArticleRd=articleService.writeArticle(loginedMemberId,title,body);
		int id=writeArticleRd.getData1();
		Article article=articleService.getForPrintArticle(loginedMemberId,id);
		
		return ResultData.newData(writeArticleRd,"article",article);
	}
	
	@RequestMapping("/usr/article/list")
	public String showList(HttpSession httpSession,Model model) {
		boolean isLogined =false;
		int loginedMemberId=0;
		
		if(httpSession.getAttribute("loginedMemberId")!=null) {
			isLogined=true;
			loginedMemberId=(int)httpSession.getAttribute("loginedMemberId");
		}
		
		List<Article> articles= articleService.getForPrintArticles(loginedMemberId);
		model.addAttribute("articles",articles);
		return "usr/article/list";
	}
	
	@RequestMapping("/usr/article/detail")
	public String showDetail(HttpSession httpSession,Model model,int id) {
		boolean isLogined =false;
		int loginedMemberId=0;
		
		if(httpSession.getAttribute("loginedMemberId")!=null) {
			isLogined=true;
			loginedMemberId=(int)httpSession.getAttribute("loginedMemberId");
		}
		
		Article article= articleService.getForPrintArticle(loginedMemberId,id);
		model.addAttribute("article",article);
		return "usr/article/detail";
	}
	
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData<Article> getArticle(HttpSession httpSession,int id) {
		boolean isLogined =false;
		int loginedMemberId=0;
		
		if(httpSession.getAttribute("loginedMemberId")!=null) {
			isLogined=true;
			loginedMemberId=(int)httpSession.getAttribute("loginedMemberId");
		}
		
		Article article=articleService.getForPrintArticle(loginedMemberId,id);
		if(article==null) {
			return ResultData.from("F-1",Ut.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		
		return ResultData.from("S-1",Ut.f("%d번 게시물입니다.", id),"article",article);
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData<Article> doDelete(HttpSession httpSession, int id) {
		boolean isLogined =false;
		int loginedMemberId=0;
		if(httpSession.getAttribute("loginedMemberId")!=null) {
			isLogined=true;
			loginedMemberId=(int)httpSession.getAttribute("loginedMemberId");
		}
		
		Article article=articleService.getForPrintArticle(loginedMemberId,id);
		
		if(article==null) {
			return ResultData.from("F-1",Ut.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		
		if(isLogined==false) {
			return ResultData.from("F-A", "로그인 후 이용해주세요.");
		}
		
		if(loginedMemberId!=article.getMemberId()) {
			return ResultData.from("F-2","삭제 권한이 없습니다.");
		}
		
		
		articleService.deleteArticle(id);
		return ResultData.from("S-1",Ut.f("%d번 게시물을 삭제하였습니다.", id),"article",article);
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData<Article> doModify(HttpSession httpSession,int id,String title,String body) {
		boolean isLogined=false;
		int loginedMemberId=0;
		
		if(httpSession.getAttribute("loginedMemberId")!=null) {
			isLogined=true;
			loginedMemberId=(int)httpSession.getAttribute("loginedMemberId");
		}
		
		if(isLogined==false) {
			return ResultData.from("F-A", "로그인 후 이용해주세요.");
		}
		
		Article article=articleService.getForPrintArticle(loginedMemberId,id);
		
		if(article==null) {
			return ResultData.from("F-1",Ut.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		
		ResultData actorCanModifyRd= articleService.actorCanModify(loginedMemberId,article);
		
		if(actorCanModifyRd.isFail()) {
			return actorCanModifyRd;
		}
		
		return articleService.modifyArticle(id,title,body);
		
	}

	
	
}
