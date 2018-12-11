package interceptor

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class CacheNetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // キャッシュがない場合、キャッシュを作る
        return chain.proceed(chain.request()).newBuilder()
                .removeHeader("Pragma")
                // リクエストに最大６０秒のキャッシュを設置する
                .addHeader("Cache-Control", "max-age=60")
                .build()
    }

}

internal class CacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response: Response

        val cacheControl = if (true) {
            // TODO ネット有無をチェックする
            // ネットがある場合、１０秒以内のキャッシュをチェックする
            CacheControl.Builder()
                    .maxAge(10, TimeUnit.SECONDS)
                    .build()
        } else {
            // ネットがない場合、３０日以内のキャッシュをチェックする
            CacheControl.Builder()
                    .maxStale(30, TimeUnit.DAYS)
                    .build()
        }

        val request = chain.request()
                .newBuilder()
                .cacheControl(cacheControl)
                .build()

        response = chain.proceed(request)
        return response.newBuilder().build()
    }

}