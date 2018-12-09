package services

import okhttp3.Interceptor
import okhttp3.Response

class AddCookiesInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()

        val cookies = getCookie()

        for (cookie in cookies) {
            builder.addHeader("Cookie", cookie)
        }

        return chain.proceed(builder.build())
    }

    private fun getCookie(): List<String> {
        val cookies = mutableListOf<String>()
        cookies.add("JSESSIONID=0B572452687E267E516493E7264014AF")
        cookies.add("loginUserName=cnszz39")
        cookies.add("token_pass=83e013db1014c4d13a316ff1fbc0d15d")
        return cookies
    }
}