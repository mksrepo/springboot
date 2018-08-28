package com.sakx.developer.demo.service;

import com.sakx.developer.demo.Application;
import com.sakx.developer.demo.model.Article;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:Before-Test-Schema.sql")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:Before-Test-Data.sql")
public class ArticlesCatalogTests {

    private static final Logger logger = LoggerFactory.getLogger(ArticlesCatalogTests.class);

    @LocalServerPort
    private int port;

    @Rule
    public TestName tname = new TestName();

    @Autowired
    private ArticlesCatalog service;

    @Test
    public void testAutowires() throws Exception {
        Assert.assertNotNull("... service instance is null/empty", service);
    }

    @Test
    public void testGetArticleById() throws Exception {
        int id = 1;
        Article article = service.getArticleById(id);
        Assert.assertNotNull("... returned object is null/empty", article);
        System.out.println(" returned article for id " + id + ", " + article);
    }

    @Test
    public void testGetAllArticles() {
        List<Article> results = service.getAllArticles();
        logger.debug(results.size() + ">>>" + results);
        logger.debug(tname.getMethodName() + " " + results);

        logger.info(" returned results - \n {}", results);

        Assert.assertTrue("returned no of rows is incorrect", (results.size() == 4));
    }

}
