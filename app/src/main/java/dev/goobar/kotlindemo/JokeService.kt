package dev.goobar.kotlindemo

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.http.GET

@Serializable
data class Joke(
  val setup: String,
  val punchline: String
)

interface JokeService {
  @GET("joke")
  suspend fun getJoke(): Joke
}

@Module
@InstallIn(ViewModelComponent::class)
object ServiceModule {

  @OptIn(ExperimentalSerializationApi::class)
  @Provides
  fun provideJokeService(): JokeService {
    return Retrofit.Builder()
      .baseUrl("http://10.0.2.2:8080/")
      .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
      .build()
      .create(JokeService::class.java)
  }
}