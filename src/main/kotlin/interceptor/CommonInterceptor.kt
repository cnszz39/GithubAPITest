package interceptor

import okhttp3.Interceptor
import okhttp3.Response

class CommonInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest = chain.request()
        val authorizedUrlBuilder = with(oldRequest.url()) {
            this.newBuilder()
                    .scheme(this.scheme())
                    .host(this.host())
                    //TODO tokenをプロパティファイルに移動
                    .addQueryParameter("access_token", "e804013088daeb4162a872ce23e565a7dadae680")
        }
        val request = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(authorizedUrlBuilder.build())
                .build()
        return chain.proceed(request)
    }

}