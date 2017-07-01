package com.yannshu.epicpicturesofearth.di.app

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.yannshu.epicpicturesofearth.data.network.PictureMetadataApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class NetModule {
    @Provides
    fun providePicturesMetadataApi(retrofit: Retrofit): PictureMetadataApi {
        return retrofit.create(PictureMetadataApi::class.java)
    }

    @Provides
    fun provideRetrofit(@Named("ImageMetadataApi") baseUrl: String, httpClient: OkHttpClient,
                        gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addConverterFactory(gsonConverterFactory)
                .build()
    }

    @Provides
    @Named("ImageMetadataApi")
    fun provideMetadataApiUrl(): String {
        return "https://epic.gsfc.nasa.gov/api/"
    }

    @Provides
    fun provideGsonConverterFactoru(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
    }

    @Provides
    fun provideOkHttpClient(logInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(logInterceptor)
                .build()
    }

    @Provides
    fun provideLogInterceptor(): HttpLoggingInterceptor {
        var logInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return logInterceptor
    }
}
