package com.sakx.developer.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.DefaultResponseErrorHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sakx.developer.demo.Application;
import com.sakx.developer.demo.exception.ErrorInfo;
import com.sakx.developer.demo.model.Article;

import lombok.Getter;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:Before-Test-Schema.sql")
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:Before-Test-Data.sql")
@AutoConfigureMockMvc
public class ArticleRestControllerTests {

	@SuppressWarnings("serial")
	public static class ServiceException extends RuntimeException {

		private @Getter HttpStatus status;

		private @Getter ErrorInfo errorInfo;

		public HttpStatus getStatus() {
			return status;
		}

		public void setStatus(HttpStatus status) {
			this.status = status;
		}

		public ErrorInfo getErrorInfo() {
			return errorInfo;
		}

		public void setErrorInfo(ErrorInfo errorInfo) {
			this.errorInfo = errorInfo;
		}

		public ServiceException(HttpStatus status, ErrorInfo info) {
			super(info.getMessage());
			this.status = status;
			this.errorInfo = info;
		}

	}

	private static class ResponseErrorHandler extends DefaultResponseErrorHandler {
		@Override
		public void handleError(ClientHttpResponse response) throws IOException {
			HttpStatus status = response.getStatusCode();
			if (hasError(status)) {
				byte[] resp = getResponseBody(response);
				ObjectMapper mapper = new ObjectMapper();
				ErrorInfo info = mapper.readValue(resp, 0, resp.length, ErrorInfo.class);
				throw new ServiceException(status, info);
			}
			super.handleError(response);
		}
	}

	public static final Logger logger = LoggerFactory.getLogger(ArticleRestControllerTests.class);

	@LocalServerPort
	private int port;

	@Rule
	public TestName tname = new TestName();

	@Autowired
	private TestRestTemplate restTemplate;

	private HttpHeaders headers = new HttpHeaders();

	@Before
	public void setup() {
		// The TestRestTemplate squelches any exceptions. We need to validate
		// them.
		restTemplate.getRestTemplate().setErrorHandler(new ResponseErrorHandler());
	}

	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		logger.info("\n -----------------------------> test (+) {}", tname.getMethodName());
		assertThat(restTemplate.getForObject(createURLWithPort("/"), String.class)).contains("Article service");
		logger.info("\n -----------------------------> test (-) {}", tname.getMethodName());
	}

	@Test
	public void shouldReturnArticleForId() throws Exception {
		logger.info("\n -----------------------------> test (+) {}", tname.getMethodName());
		String expected = "{\"articleId\":1,\"title\":\"Spring REST Security using Hibernate\",\"category\":\"Spring\"}";

		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<Article> response = restTemplate.exchange(createURLWithPort("/articles/show/1"), HttpMethod.GET,
				(new HttpEntity<String>(null, headers)), Article.class);
		System.out.println(response);
		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().toJson()).isEqualToIgnoringCase(expected);
		logger.info("\n -----------------------------> test (-) {}", tname.getMethodName());
	}

	@Test
	public void shouldReturnNotFoundForId() throws Exception {
		logger.info("\n -----------------------------> test (+) {}", tname.getMethodName());
		headers.setContentType(MediaType.APPLICATION_JSON);
		try {
			restTemplate.exchange(createURLWithPort("/articles/show/100"), HttpMethod.GET,
					(new HttpEntity<String>(null, headers)), Article.class);
		} catch (ServiceException ex) {
			assertThat(ex.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
			assertThat(ex.getErrorInfo().getCode()).isEqualTo("article.not-found");
		}
		logger.info("\n -----------------------------> test (-) {}", tname.getMethodName());
	}

	@Test
	public void shouldReturnAllArticles() throws Exception {
		logger.info("\n -----------------------------> test (+) {}", tname.getMethodName());
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<Collection<Article>> response = restTemplate.exchange(createURLWithPort("/articles/list"),
				HttpMethod.GET, (new HttpEntity<String>(null, headers)),
				new ParameterizedTypeReference<Collection<Article>>() {
				});
		System.out.println(response);
		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().size()).isEqualTo(4);

		response.getBody().forEach(a -> System.out.println(a.toJson()));
		logger.info("\n -----------------------------> test (-) {}", tname.getMethodName());
	}

	@Test
	public void shouldAddArticleAndReturnId() {
		logger.info("\n -----------------------------> test (+) {}", tname.getMethodName());

		Article article = new Article(-1, "The Phoenix Project", "Management");

		HttpEntity<Article> entity = new HttpEntity<Article>(article, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/articles/add"), HttpMethod.POST,
				entity, String.class);

		System.out.println(response);
		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		logger.info("\n -----------------------------> test (-) {}", tname.getMethodName());
	}

	@Test
	public void shouldNotAddDuplicateArticle() {
		logger.info("\n -----------------------------> test (+) {}", tname.getMethodName());

		Article article = new Article(-1, "POSA1", "Architecture");

		HttpEntity<Article> entity = new HttpEntity<Article>(article, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/articles/add"), HttpMethod.POST,
				entity, String.class);
		try {
			restTemplate.exchange(createURLWithPort("/articles/add"), HttpMethod.POST, entity, String.class);
			fail("Should not have added article");
		} catch (ServiceException ex) {
			Map<String, Object> values = new HashMap<>();
			values.put("articleId", article.getArticleId());
			values.put("category", article.getCategory());
			values.put("title", article.getTitle());
			assertThat(ex.getStatus()).isEqualTo(HttpStatus.CONFLICT);
			assertThat(ex.getErrorInfo().getCode()).isEqualTo("article.exists");
			assertThat(ex.getErrorInfo().getPayload()).isEqualTo(values);
		}
		logger.info("\n -----------------------------> test (-) {}", tname.getMethodName());
	}

	@Test
	public void shouldUpdateArticle() {
		logger.info("\n -----------------------------> test (+) {}", tname.getMethodName());

		Article article = new Article(2, "Cloud Native Java", "Cloud Native");

		HttpEntity<Article> entity = new HttpEntity<Article>(article, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/articles/update/2"), HttpMethod.PUT,
				entity, String.class);

		System.out.println(response);
		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();

		// verify delete worked by searching for it
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<Article> response2 = restTemplate.exchange(createURLWithPort("/articles/show/2"), HttpMethod.GET,
				(new HttpEntity<String>(null, headers)), Article.class);
		System.out.println(response2);
		assertThat(response2).isNotNull();
		assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response2.getBody()).isNotNull();
		assertThat(response2.getBody().getCategory()).isEqualToIgnoringCase("Cloud Native");

		logger.info("\n -----------------------------> test (-) {}", tname.getMethodName());
	}

	@Test
	public void shouldDeleteArticle() {
		logger.info("\n -----------------------------> test (+) {}", tname.getMethodName());

		// delete
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<String> response1 = restTemplate.exchange(createURLWithPort("/articles/delete/3"),
				HttpMethod.DELETE, (new HttpEntity<String>(null, headers)), String.class);
		System.out.println(response1);
		assertThat(response1).isNotNull();
		assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.OK);

		// verify delete worked by searching for it
		headers.setContentType(MediaType.APPLICATION_JSON);
		try {
			restTemplate.exchange(createURLWithPort("/articles/show/3"), HttpMethod.GET,
					(new HttpEntity<String>(null, headers)), Article.class);
		} catch (ServiceException ex) {
			assertThat(ex.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
		}
		logger.info("\n -----------------------------> test (-) {}", tname.getMethodName());
	}

	// helper method to build uri
	private String createURLWithPort(String uri) {
		String url = "http://localhost:" + port + uri;
		System.out.println(" url - " + url);
		return url;
	}

}
