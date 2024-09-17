package com.example.data.api.model

import android.annotation.SuppressLint
import android.util.Log
import com.example.data.api.NewsServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @SuppressLint("SuspiciousIndentation")
    @Provides
    fun provideLoggingInterceptor():HttpLoggingInterceptor{
         val loggingInterceptor = HttpLoggingInterceptor{message->
            Log.e("api",message)
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
          return loggingInterceptor
    }
    @Provides
    fun provideOkHttpClient(loggingInterceptor:HttpLoggingInterceptor):OkHttpClient{
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }
   @Provides
   fun provideGsonConverterFactory():GsonConverterFactory{
       return GsonConverterFactory.create()
   }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient
    ,gsonConverterFactory: GsonConverterFactory
    ):Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }
    @Provides
    fun getApi(retrofit: Retrofit):NewsServices{
        return retrofit.create(NewsServices::class.java)
    }

}