package com.sakx.developer.demo.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleRowMapper implements RowMapper<Article> {

	@Override
	public Article mapRow(ResultSet row, int rowNum) throws SQLException {
		Article article = new Article();
		article.setArticleId(row.getInt("article_id"));
		article.setTitle(row.getString("title"));
		article.setCategory(row.getString("category"));
		return article;
	}

}
