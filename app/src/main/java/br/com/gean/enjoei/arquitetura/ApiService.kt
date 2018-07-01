package br.com.gean.enjoei.arquitetura

import br.com.gean.enjoei.utils.Constantes
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiService {

    val service: Api

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(logging)
        }

        val gson = GsonBuilder().setLenient().create()
        val url = StringBuilder(Constantes.URL_WEBSERVICE)
        val retrofit = Retrofit.Builder()
                .baseUrl(url.toString())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient
                        .connectTimeout(Constantes.TEMPO_TIMEOUT, TimeUnit.SECONDS)
                        .readTimeout(Constantes.TEMPO_TIMEOUT, TimeUnit.SECONDS)
                        .writeTimeout(Constantes.TEMPO_TIMEOUT, TimeUnit.SECONDS)
                        .build())
                .build()

        service = retrofit.create<Api>(Api::class.java)
    }

}
