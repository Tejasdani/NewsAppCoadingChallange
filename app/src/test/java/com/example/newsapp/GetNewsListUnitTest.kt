package com.example.newsapp

import com.example.newsapp.api.repository.NewsRepository
import com.example.newsapp.api.response.NewsResponse
import com.example.newsapp.api.service.NewsService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import org.junit.Assert.*
import org.mockito.ArgumentMatchers.anyString
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.net.HttpURLConnection

/*
Created By Tejas Dani on 18/Dec/2023
* to test API using mock server
*
*/

class GetNewsListUnitTest {


    private lateinit var mockWebServer: MockWebServer
    private lateinit var repository: NewsRepository
    private lateinit var mockedResponse: String
    private lateinit var newsService: NewsService
    private val gson = GsonBuilder()
        .setLenient()
        .create()

    @Before
    fun setUp() {

        //Local WebServer were we have already added dependency for this
        mockWebServer = MockWebServer()
        mockWebServer.start()
        var BASE_URL = mockWebServer.url("/").toString()

        val okHttpClient = OkHttpClient
            .Builder()
            .build()
        val service = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

        newsService = service.create(NewsService::class.java)
        repository = NewsRepository(newsService)
    }

    // To test weather response is not null and giving HTTP_OK result
    @Test
    fun testApiOK() = runTest {
        mockedResponse = MockResponseFileReader("dashDetailsApi/success.json").content

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(mockedResponse)
        )

        val response = repository.getNewsList(anyString(), anyString(), 100, anyString())
        assertNotNull(response.value)
        assertEquals(response.value, Gson().fromJson(mockedResponse, NewsResponse::class.java))
    }


    // Test for actual response is return
    @Test
    fun testAPISuccess() = runTest {
        mockedResponse = MockResponseFileReader("dashDetailsApi/success.json").content
        assertNotNull(mockedResponse)

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(mockedResponse)
        )
        val response = repository.getNewsList(anyString(), anyString(), 100, anyString())

        //Todo Callers should use this to verify the request was sent as intended
        // to ensure that response coming from server we are taking that response.
        mockWebServer.takeRequest()// Very important
    }


    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
