package com.lkh.example.demo.repository;



import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lkh.example.demo.vo.Article;

@Mapper
public interface ArticleRepository {
	
	//insert into article set regdate = NOW(), updateDate = NOW() , title = ? , body = ?
	public void writeArticle(@Param("memberId") int memberId,@Param("title") String title,@Param("body") String body);
	
	//select * from article where id = ?
	public Article getArticle(@Param("id") int id);
			
	//delete from article where id = ?
	public void deleteArticle(@Param("id") int id);
	
	//update article set title = ? , boyd = ? , updateDate = NOW() where id = ?
	public void modifyArticle(@Param("id") int id,@Param("title") String title,@Param("body") String body);
	
	//select * from article order by id desc
	public List<Article> getArticles();
	
	public int getLastInsertId();
}
