package com.sakx.developer.demo.repository;

import com.sakx.developer.demo.model.Article;
import com.sakx.developer.demo.model.ArticleRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Transactional
@Repository
public class ArticleRepository {

	public static final Logger logger = LoggerFactory.getLogger(ArticleRepository.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Article> getAllArticles() {
		String sql = "SELECT article_id, title, category FROM articles";
		RowMapper<Article> rowMapper = new ArticleRowMapper();
		List<Article> articles = jdbcTemplate.query(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				final PreparedStatement ps = connection.prepareStatement(sql);
				return ps;
			}
		}, rowMapper);
		return articles;
	}

	public Article getArticleById(int articleId) {
		String sql = "SELECT article_id, title, category FROM articles WHERE article_id = ?";
		RowMapper<Article> rowMapper = new BeanPropertyRowMapper<Article>(Article.class);
		List<Article> articles = jdbcTemplate.query(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				final PreparedStatement ps = connection.prepareStatement(sql);
				int idx = 0;
				ps.setInt(++idx, articleId);
				return ps;
			}
		}, rowMapper);
		return (articles != null && articles.isEmpty() == false ? articles.get(0) : null);
	}

	public List<Article> getArticleByTitleAndCategory(String title, String category) {
		String sql = "SELECT * FROM articles WHERE title = ? and category=?";
		RowMapper<Article> rowMapper = new BeanPropertyRowMapper<Article>(Article.class);
		List<Article> articles = jdbcTemplate.query(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				final PreparedStatement ps = connection.prepareStatement(sql);
				int idx = 0;
				ps.setString(++idx, title);
				ps.setString(++idx, category);
				return ps;
			}
		}, rowMapper);
		return articles;
	}
	
	public List<Article> getArticleByCategory(String articleCategory) {
		String sql = "SELECT article_id, title, category FROM articles WHERE category = ?";
		RowMapper<Article> rowMapper = new BeanPropertyRowMapper<Article>(Article.class);
		List<Article> articles = jdbcTemplate.query(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				final PreparedStatement ps = connection.prepareStatement(sql);
				int idx = 0;
				ps.setString(++idx, articleCategory);
				return ps;
			}
		}, rowMapper);
		return (articles != null && articles.isEmpty() == false ? articles : null);
	}


    //Add article
	public int addArticle(Article article) {
		String sqlInsert = "INSERT INTO articles (title, category) values (?, ?)";
		KeyHolder key = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				final PreparedStatement ps = connection.prepareStatement(sqlInsert,
												Statement.RETURN_GENERATED_KEYS);
				int idx = 0;
				ps.setString(++idx, article.getTitle());
				ps.setString(++idx, article.getCategory());
				return ps;
			}
		}, key);

		logger.info(" added article with id {}", key.getKey());
		int id = (key.getKey() != null ? key.getKey().intValue() : -1);
		article.setArticleId(id);
		return id;
	}

	public int updateArticle(Article article) {
		String sql = "UPDATE articles SET title=?, category=? WHERE article_id=?";

		int rowcnt = jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				final PreparedStatement ps = connection.prepareStatement(sql);
				int idx = 0;
				ps.setString(++idx, article.getTitle());
				ps.setString(++idx, article.getCategory());
				ps.setInt(++idx, article.getArticleId());
				return ps;
			}
		});
		return rowcnt;
	}

	public int deleteArticle(int articleId) {
		String sql = "DELETE FROM articles WHERE article_id=?";
		int rowcnt = jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				final PreparedStatement ps = connection.prepareStatement(sql);
				int idx = 0;
				ps.setInt(++idx, articleId);
				return ps;
			}
		});
		return rowcnt;
	}

	public boolean isExists(String title, String category) {
		boolean isExists = true;
		List<Article> articles =getArticleByTitleAndCategory(title, category);
		if (articles == null || articles.isEmpty()) {
			isExists = false;
		}
		return isExists;
	}
}
