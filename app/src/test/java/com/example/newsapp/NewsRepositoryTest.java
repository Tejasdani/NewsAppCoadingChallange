package com.example.newsapp;

import static org.junit.Assert.assertEquals;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.newsapp.api.repository.NewsRepository;
import com.example.newsapp.api.response.NewsResponse;
import com.example.newsapp.api.service.NewsService;
import com.example.newsapp.model.News;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private MockWebServer mockWebServer;

    @Mock
    private NewsService newsService;

    private NewsRepository newsRepository;

    @Before
    public void setUp() {
        mockWebServer = new MockWebServer();
        newsRepository = new NewsRepository(getMockNewsService());
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    //todo test cases for check null response, and getting response from "getArticles()" method
    @Test
    public void testGetNewsList() throws InterruptedException {
        // Arrange
        String mockResponse = null;
        try {
            mockResponse = new MockResponseFileReader("dashDetailsApi/success.json").getContent();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mockWebServer.enqueue(new MockResponse().setBody(mockResponse).setResponseCode(200));

        NewsResponse result = LiveDataTestUtil.getValue(newsRepository.getNewsList("us", "business", 10, "apiKey"));

        // Assert
        Assert.assertNotNull(result);

        // assertions based on your expected result and compare it with the 'result'
        assertEquals(3, result.getArticles().size()); // Assuming there are 3 articles in the mock response


        List<News>  firstArticle = result.getArticles();
        assertEquals("Hakyung Kim", firstArticle.get(0).getAuthor());
        assertEquals("Stock futures are all rise after major averages rally for seven consecutive weeks: Live updates - CNBC", firstArticle.get(0).getTitle());

    }


    private NewsService getMockNewsService() {
        return new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsService.class);
    }

}
