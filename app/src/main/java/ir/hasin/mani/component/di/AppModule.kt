package ir.hasin.mani.component.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.hasin.mani.BuildConfig
import ir.hasin.mani.model.datasource.MovieWebApiDatasource
import ir.hasin.mani.model.repository.MovieRepository
import ir.hasin.mani.model.repository.MovieRepositoryImpl
import ir.hasin.mani.util.getOkHttpClient
import ir.hasin.mani.util.getRetrofitClient
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Named("BASE_URL")
    fun provideBaseUrl(): String = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = getOkHttpClient()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        @Named("BASE_URL") BASE_URL: String,
    ): Retrofit = getRetrofitClient(BASE_URL, okHttpClient)

    @Provides
    @Singleton
    fun getUserWebApiDatasource(retrofit: Retrofit): MovieWebApiDatasource =
        retrofit.create(MovieWebApiDatasource::class.java)

    @Provides
    @Singleton
    fun provideMovieRepository(datasource: MovieWebApiDatasource): MovieRepository =
        MovieRepositoryImpl(datasource)

}