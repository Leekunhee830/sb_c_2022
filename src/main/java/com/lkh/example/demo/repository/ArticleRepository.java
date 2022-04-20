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

	// insert into article set regdate = NOW(), updateDate = NOW() , title = ? ,
	// body = ?
	public void writeArticle(@Param("memberId") int memberId,@Param("boardId") int boardId ,@Param("title") String title, @Param("body") String body);

	// select * from article where id = ?
	@Select("""
			SELECT A.*,
			M.nickname AS extra__writerName
			FROM article AS A
			LEFT JOIN member AS M
			ON A.memberId=M.id
			where A.id = #{id}
			""")
	public Article getArticle(@Param("id") int id);

	// delete from article where id = ?
	public void deleteArticle(@Param("id") int id);

	// update article set title = ? , boyd = ? , updateDate = NOW() where id = ?
	public void modifyArticle(@Param("id") int id, @Param("title") String title, @Param("body") String body);

	// select * from article order by id desc
	@Select("""
			<script>
			SELECT A.*,
			M.nickname AS extra__writerName
			FROM article AS A
			LEFT JOIN member AS M
			ON A.memberId=M.id
			WHERE 1
			<if test="boardId!=0">
				AND A.boardId=#{boardId}
			</if>
			ORDER BY A.id DESC
			</script>
				""")
	public List<Article> getArticles(@Param("boardId") int boardId);

	public int getLastInsertId();
	
	@Select("""
			<script>
			SELECT COUNT(*) AS cnt
			FROM article AS A
			WHERE 1
			<if test="boardId!=0">
				AND A.boardId=#{boardId}
			</if>
			</script>
				""")
	public int getArticlesCount(@Param("boardId") int boardId);
}
