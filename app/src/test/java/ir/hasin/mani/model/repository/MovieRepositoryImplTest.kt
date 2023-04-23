package ir.hasin.mani.model.repository

import com.google.gson.Gson
import ir.hasin.mani.model.datasource.MovieWebApiDatasource
import ir.hasin.mani.model.dto.MovieResult
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.net.HttpURLConnection

class MovieRepositoryImplTest{
    private lateinit var repository: MovieRepository
    private lateinit var testApis: MovieWebApiDatasource
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        repository = MovieRepositoryImpl(testApis)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `get movie list, api must return empty with http code 200`() = runTest {
        val users = emptyList<MovieResult>()
        val expectedResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(Gson().toJson(users))
        mockWebServer.enqueue(expectedResponse)

        val actualResponse = repository.getMovieList()
//        assertThat(actualResponse.).hasSize(0)
    }
}