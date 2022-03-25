package com.lkh.example.demo.repository;



import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.lkh.example.demo.vo.Article;

@Mapper
public interface ArticleRepository {
	
	//insert into article set regdate = NOW(), updateDate = NOW() , title = ? , body = ?
	public Article writeArticle(String title,String body);
	
	//select * from article where id = ?
	@Select("select * from article where id = #{id}")
	public Article getArticle(@Param("id") int id);
			
	//delete from article where id = ?
	public void deleteArticle(int id);
	
	//update article set title = ? , boyd = ? , updateDate = NOW() where id = ?
	public void modifyArticle(int id, String title, String body);
	
	//select * from article order by id desc
	public List<Article> getArticles();
}
