package com.sakx.developer.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sakx.developer.demo.exception.ArticleAlreadyExistsException;
import com.sakx.developer.demo.exception.ArticleNotFoundException;
import com.sakx.developer.demo.exception.BadRequestException;
import com.sakx.developer.demo.model.Article;
import com.sakx.developer.demo.repository.ArticleRepository;

/**
 * This classes contains the service catalog for articles
 */

@Service
public class ArticlesCatalog {

	@Autowired
	private ArticleRepository articleRepository;

	@Value("${category.max-length:5}")
	private int maxLength;

	public String getInfo() {
		return "Article service";
	}

	public Article getArticleById(int articleId) throws ArticleNotFoundException {
		Article obj = articleRepository.getArticleById(articleId);
		if (null == obj) {
			throw new ArticleNotFoundException("article.not-found",
					"No article was found when searching by article id");
		}
		return obj;
	}

	public List<Article> getAllArticles() {
		return articleRepository.getAllArticles();
	}

	public List<Article> getArticleByCategory(String articleCategory)
			throws BadRequestException, ArticleNotFoundException {
		validateWellFormedCategory(articleCategory);
		List<Article> articles = articleRepository.getArticleByCategory(articleCategory);
		if (null == articles) {
			throw new ArticleNotFoundException("category.no-articles",
					"Search by category did not return any articles");
		}
		return articles;
	}

	private void validateWellFormedCategory(String articleCategory) throws BadRequestException {
		// START - Business Logic
		// to ensure all characters are not digit
		if (articleCategory.length() > maxLength) {
			throw new BadRequestException("category.invalid.length",
					String.format("Category must have more than %d characters", maxLength));
		}
		boolean containsDigitsOnly = hasOnlyDigits(articleCategory);
		if (containsDigitsOnly) {
			throw new BadRequestException("category.invalid.numeric", "Category must not be numeric");
		}
		// END - Business Logic
	}

	private boolean hasOnlyDigits(String articleCategory) {
		int j = 0;
		for (int i = 0; i < articleCategory.length(); i++) {
			if (Character.isDigit(articleCategory.charAt(i))) {
				j++;
			}
		}
		boolean containsDigitsOnly = (j == articleCategory.length());
		return containsDigitsOnly;
	}

	// public synchronized boolean addArticle(Article article) {
	public synchronized Article addArticle(Article article) throws ArticleAlreadyExistsException {
		if (!articleRepository.isExists(article.getTitle(), article.getCategory())) {
			if (articleRepository.addArticle(article) > 0) {
				return article;
			}
		}
		throw new ArticleAlreadyExistsException("article.exists", "Article already exists");
	}

	public int updateArticle(Article article) {
		return articleRepository.updateArticle(article);
	}

	public int deleteArticle(int articleId) {
		return articleRepository.deleteArticle(articleId);
	}
}
